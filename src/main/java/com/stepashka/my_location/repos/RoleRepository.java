package com.stepashka.my_location.repos;

import com.stepashka.my_location.models.Role;
import com.stepashka.my_location.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

//TODO 11a repo with customsRole (enable to delete a user with a role)
public interface RoleRepository extends CrudRepository<Role, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM userroles WHERE userid = :userid AND roleid = :roleid",
            nativeQuery = true)
    JustTheCount checkUserRolesCombo(long userid,
                                     long roleid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserRoles WHERE userid = :userid AND roleid = :roleid")
    void deleteUserRoles(long userid,
                         long roleid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO userroles(userid, roleid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:userid, :roleid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertUserRoles(String uname,
                         long userid,
                         long roleid);

    Role findByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    // user Role instead of roles in order to use Hibernate SQL
    @Query(value = "UPDATE roles SET name = :name, last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP WHERE roleid = :roleid",
            nativeQuery = true)
    void updateRoleName(String uname,
                        long roleid,
                        String name);
}