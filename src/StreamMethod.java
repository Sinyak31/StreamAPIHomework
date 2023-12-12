

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMethod {


    private static void forEachMethod(List<Patient> patients) {
        patients.stream()
                .forEach(p -> System.out.println(p.getExpenses() + p.getFio()));
    }

    private static void noneMatchMethod(List<Patient> patients) {
        boolean bool = patients.stream()
                .noneMatch(p -> (p.getBirthDate().getYear() < LocalDate.now().getYear() - 100));
        if (bool)
            System.out.println("Пациентов старше 100 лет нет");
        else System.out.println("Пациент старше 100 лет есть");
    }

    private static void collectMethod(List<Patient> patients) {
        Map<LocalDate, List<String>> pat;
        pat = patients.stream()
                .collect(Collectors.groupingBy(
                        Patient::getBirthDate,
                        Collectors.mapping(
                                Patient::getFio,
                                Collectors.toList()
                        )
                ));
        System.out.println(pat.entrySet());
    }

    private static void findFirstMethod(List<Patient> patients) {
        System.out.println(patients.stream()
                .filter(patient -> patient.getBirthDate()
                        .getYear() == 1973)
                .findFirst());
    }

    private static void anyMatchMethod(List<Patient> patients) {
        boolean anyMatch = patients.stream()
                .anyMatch(p -> (p.getBirthDate().getYear() < 1923));
        if (anyMatch)
            System.out.println("Пациент старше 100 лет есть");
        else System.out.println("Пациента старше 100 лет нет");
    }

    private static void allMatchMethod(List<Patient> patients) {
        boolean allMatch = patients.stream()
                .allMatch(patient -> patient.getExpenses().isEmpty());
        if (allMatch)
            System.out.println("Полностью здорового человека нет");
        else System.out.println("Полностью здоровый человек есть");
    }

    private static void minMaxMethod(List<Patient> patients) {
        Patient minPat = patients
                .stream()
                .min(Comparator.comparingInt(StreamMethod::sumExp)).get();
        Patient maxPat = patients
                .stream()
                .max(Comparator.comparingInt(StreamMethod::sumExp)).get();
        System.out.println(minPat.getFio() + "  " + sumExp(minPat));
        System.out.println(maxPat.getFio() + "  " + sumExp(maxPat));
    }

    private static int sumExp(Patient p) {
        return p.getExpenses()
                .stream()
                .reduce(Integer::sum)
                .get();
    }

    public static void start(List<Patient> patients) {
        collectMethod(patients);
        System.out.println("---------------------------------------------");
        forEachMethod(patients);
        System.out.println("---------------------------------------------");
        minMaxMethod(patients);
        System.out.println("---------------------------------------------");
        findFirstMethod(patients);
        System.out.println("---------------------------------------------");
        allMatchMethod(patients);
        System.out.println("---------------------------------------------");
        noneMatchMethod(patients);
        System.out.println("---------------------------------------------");
        anyMatchMethod(patients);
        System.out.println("---------------------------------------------");
    }
}
