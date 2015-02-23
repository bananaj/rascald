package com.bananaj.mgr.service

import com.bananaj.mgr.domain.Project
import com.bananaj.mgr.domain.Step;
import com.bananaj.mgr.domain.TestCase
import com.bananaj.mgr.domain.TestCaseInfo
import com.bananaj.user.domain.User
import com.bananaj.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("/application-context.xml")
public class TestCaseServiceTest {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ProjectService projectService

    @Autowired
    private UserService userService

    @Test
    public void testInject() {
        assertNotNull(testCaseService);
    }

    @Test
    public void testSaveTestCase() {

        TestCase testCase = testCaseService.findTestCaseById("noway");
        assertNull(testCase);

        User defaultUser = userService.getUserByUserName("mgrmanager")
        assertNotNull(defaultUser)

        Project project = new Project()
        project.setDescription("New Project")
        project.setName("Project Name")
        project.setOwner(defaultUser)

        project = projectService.saveProject(project)
        assertNotNull(project)
        assertNotNull(project.getId())

        testCase = new TestCase();
        testCase.setTitle("title");
        testCase.setProject(project)

        TestCaseInfo info = new TestCaseInfo()
        info.setSummary("summary")
        info.setPreconditions("preconditions")
        info.setTestCase(testCase)
        testCase.getInfos().add(info)

        testCase = testCaseService.saveTestCase(testCase);
        assertNotNull(testCase);
        assertNotNull(testCase.getId());
        assertNotNull(testCase.getInfos());

        testCase = testCaseService.findTestCaseById(testCase.getId());
        assertNotNull(testCase);
        assertNotNull(testCase.getId());
        assertNotNull(testCase.getInfos());
        assertTrue(testCase.getInfos().size() > 0);
    }

    @Test
    public void testTestCaseInfo() {

        User defaultUser = userService.getUserByUserName("mgrmanager")
        assertNotNull(defaultUser)

        Project project = new Project()
        project.setDescription("New Project")
        project.setName("Project Name")
        project.setOwner(defaultUser)

        project = projectService.saveProject(project)
        assertNotNull(project)
        assertNotNull(project.getId())

        TestCase testCase = new TestCase();
        testCase.setTitle("title");
        testCase.setProject(project)

        TestCaseInfo info = new TestCaseInfo()
        info.setSummary("summary")
        info.setPreconditions("preconditions")
        info.setTestCase(testCase)

        Step step1 = new Step();
        step1.setDescription("description1")
        step1.setExpected("expected1")
        step1.setTestCaseInfo(info)
        info.getSteps().add(step1)

        Step step2 = new Step()
        step2.setDescription("description2")
        step2.setExpected("expected2")
        step2.setTestCaseInfo(info)
        info.getSteps().add(step2)


        testCase.getInfos().add(info)

        testCase = testCaseService.saveTestCase(testCase);
        assertNotNull(testCase);
        assertNotNull(testCase.getId());
        assertNotNull(testCase.getInfos());

        testCase = testCaseService.findTestCaseById(testCase.getId());


        /*
        List<TestCaseInfo> testCaseInfos = testCaseService.findTestCaseInfosForTestCaseId(testCase.getId())
        assertNotNull(testCaseInfos);
        assertTrue(testCaseInfos.size() > 0)
        assertNotNull(testCaseInfos.get(0).getSteps())
        assertTrue(testCaseInfos.get(0).getSteps().size() > 2)
        */
    }
}
