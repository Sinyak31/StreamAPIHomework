import org.w3c.dom.ls.LSOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Patient> patients = Dump.getDump();

        StreamMethod.start(patients);
    }
}
