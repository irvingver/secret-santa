package better.cloud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FamilyData {

    private static final String MARIA = "Maria";
    private static final String EDGAR = "Edgar";
    private static final String IRVING = "Irving";
    private static final String ERICK = "Erick";
    private static final String DULCE = "Dulce";
    private static final String DIEGO = "Diego";
    private static final String DARLA = "Darla";
    private static final String FLAVIO = "Flavio";
    private static final String GINA = "Gina";
    private static final String ANGEL = "Angel";
    private static final String GAEL = "Gael";
    private static final String ARTURO = "Arturo";
    private static final String GABRIELA = "Gabriela";
    private static final String GABY = "Gaby";

    public static List<FamilyMember> getFamily() {
        return List.of(
                FamilyMember.builder()
                        .name(MARIA)
                        .immediateFamily(Set.of(IRVING, EDGAR))
                        .build(),
                FamilyMember.builder()
                        .name(IRVING)
                        .immediateFamily(Set.of(MARIA, EDGAR))
                        .build(),
                FamilyMember.builder()
                        .name(EDGAR)
                        .immediateFamily(Set.of(MARIA, IRVING))
                        .build(),
                FamilyMember.builder()
                        .name(ERICK)
                        .immediateFamily(Set.of(DULCE, DIEGO, DARLA))
                        .build(),
                FamilyMember.builder()
                        .name(DULCE)
                        .immediateFamily(Set.of(ERICK, DIEGO, DARLA))
                        .build(),
                FamilyMember.builder()
                        .name(DIEGO)
                        .immediateFamily(Set.of(ERICK, DULCE, DARLA))
                        .build(),
                FamilyMember.builder()
                        .name(DARLA)
                        .immediateFamily(Set.of(ERICK, DULCE, DIEGO))
                        .build(),
                FamilyMember.builder()
                        .name(FLAVIO)
                        .immediateFamily(Set.of(GINA, ANGEL, GAEL))
                        .build(),
                FamilyMember.builder()
                        .name(GINA)
                        .immediateFamily(Set.of(FLAVIO, ANGEL, GAEL))
                        .build(),
                FamilyMember.builder()
                        .name(ANGEL)
                        .immediateFamily(Set.of(FLAVIO, GINA, GAEL))
                        .build(),
                FamilyMember.builder()
                        .name(GAEL)
                        .immediateFamily(Set.of(FLAVIO, GINA, ANGEL))
                        .build(),
                FamilyMember.builder()
                        .name(ARTURO)
                        .immediateFamily(Set.of(GABY, GABRIELA))
                        .build(),
                FamilyMember.builder()
                        .name(GABY)
                        .immediateFamily(Set.of(ARTURO, GABRIELA))
                        .build(),
                FamilyMember.builder()
                        .name(GABRIELA)
                        .immediateFamily(Set.of(ARTURO, GABY))
                        .build()
        );
    }

    public static Map<String, List<String>> firstAssignment() {
        List<String> family = List.of(
                MARIA, EDGAR, IRVING, ERICK, DULCE, DIEGO, DARLA, FLAVIO, GINA, ANGEL, GAEL, ARTURO, GABY, GABRIELA);
        return family.stream()
                .collect(Collectors.toMap(
                        participant -> participant,
                        participant -> new ArrayList<>() // Empty list for initial assignments
                ));
    }
}
