package fr.ensimag.entity;

import fr.ensimag.vo.RoleVO;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class RoleTest {
    @Test
    public void testRoleToVo(){
        Role role = new Role(1, "Admin", "Super-Pouvoirs!");
        role.setUtilisateurList(new ArrayList<Utilisateur>());
        RoleVO roleVO = role.toVO();
        Assert.assertEquals((long)role.getRoleId(),(long)roleVO.getRoleId());
        Assert.assertEquals(role.getRoleLibele(),roleVO.getRoleLibele());
        Assert.assertEquals(role.getRoleDescription(),roleVO.getRoleDescription());
    }
}
