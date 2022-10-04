package me.whiteship.demospringsecurityform.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    /**
     * .with(anonymous())) 를 @WithAnonymousUser로 대체
     */
    @WithAnonymousUser
    public void index_anonymous() throws Exception {
//        mockMvc.perform(get("/").with(anonymous()))
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    /**
     * .with(user("jiwoo").roles("USER")))를 @WithMockUser(username = "jiwoo", roles = "USER")로 대체
     */
//    @WithMockUser(username = "jiwoo", roles = "USER")
    @WithUser // 위 내용으로 새롭게 정의한 어노테이션으로 더 간결하게 설정
    public void index_user() throws Exception {
        /**
         * 이미 로그인 되어 있는 상태일 경우의 mocking(가짜, 가정) 테스트
         */
//        mockMvc.perform(get("/").with(user("jiwoo").roles("USER")))
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "jiwoo", roles = "USER")
    @WithUser
    public void admin_user() throws Exception {
//        mockMvc.perform(get("/admin").with(user("jiwoo").roles("USER")))
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "jiwoo", roles = "ADMIN")
    public void admin_admin() throws Exception {
//        mockMvc.perform(get("/admin").with(user("jiwoo").roles("ADMIN")))
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}