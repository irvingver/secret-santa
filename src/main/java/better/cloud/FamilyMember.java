package better.cloud;

import lombok.Builder;

import java.util.Set;

/**
 * Family Member Class
 * Includes the name of the member and Immediate Family
 * 
 * @author irving.velazquez 
 */
@Builder
public class FamilyMember {
    String name;
    Set<String> immediateFamily;
}
