package better.cloud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SecretSantaTest {

    private Map<String, List<String>> assignments = new HashMap<>();

    @BeforeEach
    void init() {
        assignments = new HashMap<>(FamilyData.firstAssignment());
    }

    @Test
    void shouldTestSecretSanta() {
        List<FamilyMember> familyMembers = FamilyData.getFamily();
        Map<String, String> secretSanta;
        SecretSanta ss = new SecretSanta();
        
        System.out.println(":::FIRST SECRET SANTA:::");
        secretSanta = ss.assignSecretSanta(familyMembers, assignments);
        storeSecretSanta(secretSanta);
        printAssignments();
        System.out.println(":::SECOND SECRET SANTA:::");
        secretSanta = ss.assignSecretSanta(familyMembers, assignments);
        storeSecretSanta(secretSanta);
        printAssignments();
        System.out.println(":::THIRD SECRET SANTA:::");
        secretSanta = ss.assignSecretSanta(familyMembers, assignments);
        storeSecretSanta(secretSanta);
        printAssignments();

        Assertions.assertAll(
                () -> Assertions.assertEquals(familyMembers.size(), assignments.size()),
                () -> Assertions.assertEquals(3, assignments.get("Maria").size()),
                () -> Assertions.assertEquals(3, assignments.get("Irving").size()),
                () -> Assertions.assertEquals(3, assignments.get("Edgar").size()),
                () -> Assertions.assertEquals(3, assignments.get("Erick").size()),
                () -> Assertions.assertEquals(3, assignments.get("Dulce").size()),
                () -> Assertions.assertEquals(3, assignments.get("Diego").size()),
                () -> Assertions.assertEquals(3, assignments.get("Darla").size()),
                () -> Assertions.assertEquals(3, assignments.get("Flavio").size()),
                () -> Assertions.assertEquals(3, assignments.get("Gina").size()),
                () -> Assertions.assertEquals(3, assignments.get("Angel").size()),
                () -> Assertions.assertEquals(3, assignments.get("Gael").size()),
                () -> Assertions.assertEquals(3, assignments.get("Arturo").size()),
                () -> Assertions.assertEquals(3, assignments.get("Gaby").size()),                
                () -> Assertions.assertEquals(3, assignments.get("Gabriela").size())                
        );
        
    }

    private void storeSecretSanta(Map<String, String> secretSanta) {
        secretSanta.forEach((giver, recipient) -> {
            assignments.computeIfAbsent(giver, key -> new ArrayList<>())
                    .add(recipient);
        });
    }

    private void printAssignments() {
        StringBuilder sb = new StringBuilder();
        assignments.forEach((key, value) -> {
            String recipients = String.join(",", value);
            sb.append(String.format("%s -> {%s} \n", key, recipients));
        });
        System.out.println(sb);
    }
}
