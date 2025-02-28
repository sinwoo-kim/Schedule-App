package org.example.todo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class SessionUserController {

    // 의존성 주입
    private final UserService userService;

//    @PostMapping("/session-login")
//    public String login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
//
//        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
//        log.info("loginResponseDto = " + loginResponseDto);
//        // 세션 조회
//        HttpSession session = request.getSession();
//        // Session 저장 (변환 된 loginResponseDto 객체를 전달하면 안되는가?)
//        session.setAttribute(Const.LOGIN_USER, loginResponseDto);
//        return "redirect:/users/session-home";
//    }


}
