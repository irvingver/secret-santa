package better.cloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * SecretSanta Class
 *
 * @author irving.velazquez
 */
public class SecretSanta {

    /**
     * assignSecretSanta : method that returns a map that contains the secret santa for each family member.
     *
     * @param pastAssignments: List<Map<String, String>>, previous List of secret santa
     * @param familyMembers:   List<FamilyMember>, list of family members
     * @return Map<String, String>: A map with secret santa for each family member
     */
    public Map<String, String> assignSecretSanta(List<FamilyMember> familyMembers,
                                            Map<String, List<String>> pastAssignments) {
        Map<String, String> assignments = new HashMap<>();

        // Fill participants from List of family members
        List<String> participants = fillParticipants(familyMembers);

        boolean success = false;

        while (!success) {
            Collections.shuffle(participants); // Random the existing participants
            assignments.clear();
            success = true;

            for (FamilyMember giver : familyMembers) {
                // Find valid recipient
                String recipient = findValidRecipient(giver.name, participants, giver.immediateFamily, pastAssignments);

                if (recipient == null) {
                    success = false; // Failed to find a valid recipient
                    break;
                }

                // Assign a recipient and remove them from participants
                assignments.put(giver.name, recipient);
                participants.remove(recipient);
            }

            // Refill participants if the loop failed
            if (!success) {
                participants = fillParticipants(familyMembers);
            }
        }
        return assignments;
    }

    /**
     * findValidRecipient : method that filter a valid recipient.
     *
     * @param giver:           String, giver to ve checked
     * @param participants:    List<FamilyMember>, List of participants
     * @param immediateFamily: Set<String>, Immediate family for each member
     * @param pastAssignments: Map<String, List<String>>, past assignments
     * @return String: Name of a valid recipient
     */
    private String findValidRecipient(String giver, List<String> participants, Set<String> immediateFamily,
                                      Map<String, List<String>> pastAssignments) {
        return participants.stream()
                .filter(recipient -> !recipient.equals(giver)) // No self-assignment
                .filter(recipient -> !immediateFamily.contains(recipient)) // Not in immediate family
                .filter(recipient -> !pastAssignments.getOrDefault(giver, List.of())
                        .contains(recipient)) // Not recently assigned
                .findFirst() // Pick the first valid recipient
                .orElse(null); // Return null if no valid recipient is found
    }

    /**
     * fillParticipants : Set the participants with the list of family members
     *
     * @param familyMembers: List<FamilyMember>, List of family members
     * @return List<String>: List of participants
     */
    private List<String> fillParticipants(List<FamilyMember> familyMembers) {
        return familyMembers.stream()
                .map(member -> member.name)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
