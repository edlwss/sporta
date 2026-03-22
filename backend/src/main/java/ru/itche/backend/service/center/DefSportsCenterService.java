//package ru.itche.backend.service.center;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.itche.backend.controller.center.payload.NewSportsCenterPayload;
//import ru.itche.backend.controller.center.payload.UpdateSportsCenterPayload;
//import ru.itche.backend.entity.reference.SportTypes;
//import ru.itche.backend.entity.valueobject.Address;
//import ru.itche.backend.entity.auth.Role;
//import ru.itche.backend.entity.SportsCenter;
//import ru.itche.backend.entity.auth.User;
//import ru.itche.backend.repository.center.SportsCenterRepository;
//import ru.itche.backend.repository.lookup.SportTypesRepository;
//import ru.itche.backend.repository.user.RoleRepository;
//import ru.itche.backend.service.user.UserService;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//@Service
//@RequiredArgsConstructor
//public class DefSportsCenterService implements SportsCenterService {
//
//    private final SportsCenterRepository centerRepository;
//    private final UserService userService;
//    private final RoleRepository roleRepository;
//
//    @Override
//    public Iterable<SportsCenter> getAll() {
//        return centerRepository.findAll();
//    }
//
//    @Override
//    public Optional<SportsCenter> findById(Long id) {
//        return centerRepository.findById(id);
//    }
//
//    @Override
//    @Transactional
//    public SportsCenter create(NewSportsCenterPayload payload) {
//        Address address = new Address(
//                payload.addressRegion(),
//                payload.addressCity(),
//                payload.addressStreet(),
//                payload.addressBuilding()
//        );
//
//        Role instructorRole = roleRepository.findByName("sportscenter")
//                .orElseThrow(() -> new IllegalArgumentException("Роль sportscenter не найдена"));
//
//
//        User user = userService.createUser(payload.login(),
//                payload.password(),
//                payload.email(),
//                payload.phone(),
//                instructorRole);
//
//        SportsCenter center = new SportsCenter(
//                null,
//                payload.name(),
//                payload.description(),
//                address,
//                payload.coordinates(),
//                user
//                );
//
//        return centerRepository.save(center);
//    }
//
//    @Override
//    @Transactional
//    public void update(Long id, UpdateSportsCenterPayload payload) {
//        Address address = new Address(
//                payload.addressRegion(),
//                payload.addressCity(),
//                payload.addressStreet(),
//                payload.addressBuilding()
//        );
//
//        centerRepository.findById(id)
//                .ifPresent(center -> {
//                    center.setName(payload.name());
//                    center.setDescription(payload.description());
//                    center.setCoordinates(payload.coordinates());
//                    center.setAddress(address);
//                });
//    }
//
//    @Override
//    @Transactional
//    public void delete(Long id) {
//        centerRepository.deleteById(id);
//    }
//}
