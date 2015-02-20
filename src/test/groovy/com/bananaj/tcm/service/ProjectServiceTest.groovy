package com.bananaj.tcm.service

import com.bananaj.tcm.domain.Project
import com.bananaj.tcm.domain.TestCase
import com.bananaj.user.domain.User
import com.bananaj.user.service.UserService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("/application-context.xml")
public class ProjectServiceTest {

    @Autowired
    private ProjectService projectService

    @Autowired
    private UserService userService

    @Test
    public void testInject() {
        assertNotNull(projectService)
    }

    @Test
    public void testProjectSave() {

        User defaultUser = userService.getUserByUserName("tcmmanager")
        assertNotNull(defaultUser)

        Project project = projectService.findProjectById("noway");
        assertNull(project);

        project = new Project()
        project.setName("Default Project")
        project.setDescription("Description")
        project.setOwner(defaultUser)

        project = projectService.saveProject(project)
        assertNotNull(project)
        assertNotNull(project.getId())
    }

}
