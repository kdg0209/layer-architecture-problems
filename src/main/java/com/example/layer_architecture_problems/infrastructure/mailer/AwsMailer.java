package com.example.layer_architecture_problems.infrastructure.mailer;

import com.example.layer_architecture_problems.domian.member.service.port.MailerService;
import org.springframework.stereotype.Component;

@Component
class AwsMailer implements MailerService {

    @Override
    public void send(String email) {
        System.out.println(email + "로 회원가입 축하 안내 문구를 발송합니다.");
    }
}
