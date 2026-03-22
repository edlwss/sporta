package ru.itche.backend.controller.center.payload;

//import ru.itche.backend.entity.SportsCenter;
//import java.util.ArrayList;
//import java.util.List;
//
//public record GetSportsCenterPayload(
//        String name,
//        String description,
//
//        String coordinates,
//        String addressRegion,
//        String addressCity,
//        String addressStreet,
//        String addressBuilding,
//
//        Long userId
//) {
//    public static GetSportsCenterPayload from(SportsCenter center) {
//
//        return new GetSportsCenterPayload(
//                center.getName(),
//                center.getDescription(),
//
//                center.getCoordinates(),
//                center.getAddress().getRegion(),
//                center.getAddress().getCity(),
//                center.getAddress().getStreet(),
//                center.getAddress().getBuilding(),
//
//                center.getUser().getId()
//        );
//    }
//
//    public static List<GetSportsCenterPayload> fromList(Iterable<SportsCenter> centers) {
//        List<GetSportsCenterPayload> result = new ArrayList<>();
//        for (SportsCenter center : centers) {
//            result.add(from(center));
//        }
//        return result;
//    }
//
//}
