package com.example.layer_architecture_problems.infrastructure.alimtalk;

import com.example.layer_architecture_problems.domian.member.service.port.AlimTalkService;
import org.springframework.stereotype.Component;

@Component
class AlimTalkServiceImpl implements AlimTalkService {

    @Override
    public void send(String phone) {
        System.out.println(phone + "로 회원가입 축하 안내 문구를 발송합니다.");
    }
}
