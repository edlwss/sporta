//package ru.itche.backend.controller.center;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;
//import ru.itche.backend.controller.center.payload.GetSportsCenterPayload;
//import ru.itche.backend.entity.SportsCenter;
//import ru.itche.backend.controller.center.payload.NewSportsCenterPayload;
//import ru.itche.backend.service.center.SportsCenterService;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/sporta/api/centers")
//public class SportsCentersRestController {
//
//    private final SportsCenterService centerService;
//
//    @GetMapping
//    public List<GetSportsCenterPayload> getAll() {
//        Iterable<SportsCenter> centers = centerService.getAll();
//        return GetSportsCenterPayload.fromList(centers);
//    }
//
//    @PostMapping("/registration")
//    public ResponseEntity<SportsCenter> create(@RequestBody NewSportsCenterPayload payload,
//                                               UriComponentsBuilder uriBuilder) {
//
//        SportsCenter created = centerService.create(payload);
//
//        return ResponseEntity.created(uriBuilder
//                        .replacePath("/sporta/api/centers/{id}")
//                        .build(Map.of("id", created.getId())))
//                .body(created);
//    }
//}
