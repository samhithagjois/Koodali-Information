package koodali.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Administrator extends Person {


    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "admin_permissions",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "permission")
    private final List<AdminPermissions> permissions = new ArrayList<>();

    public Administrator(String personID, String firstName, String lastName) {
        super(personID, firstName, lastName);
    }

    public Administrator() {
        super();
        this.personID = "NKS" + "_A_" + UUID.randomUUID();
    }

    /**
     * standard getter
     */
    public List<AdminPermissions> getPermissions() {
        return permissions;
    }

    /**
     * adds a permission to the permissions list.
     * Useful if you don't want to make a List beforehand consisting of less than two elements
     */
    public void addPermission(AdminPermissions permission) {
        permissions.add(permission);
    }
}
