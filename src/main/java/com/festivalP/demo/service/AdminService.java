package com.festivalP.demo.service;


import com.festivalP.demo.domain.Admin;
import com.festivalP.demo.domain.Member;
import com.festivalP.demo.form.AuthInfo;
import com.festivalP.demo.repository.AdminRepository;
import com.festivalP.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;


    @Transactional
    private Admin encryptFunc(Admin admin){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = admin.getAdmin_pw();
        String securePw = encoder.encode(pw);
        admin.setAdmin_pw(securePw);
        return admin;
    }

    @Transactional
    public String join(Admin admin){
        System.out.println("AdminService.join");
        adminRepository.save(encryptFunc(admin));
        return admin.getAdmin_id();
    }

    @Transactional
    public boolean validateDuplicateAdminId(String admin_id){
        Admin admin = adminRepository.findById(admin_id);
        if(admin!=null) {
            // 중복된 ID 있을 경우
            System.out.println("@@@@@@@ duplicate! @@@@");
            return false;
        }
        else{
            // 중복된 ID 없을 경우
            System.out.println("$$$$$$$$ no id in db$$$$$$$$$$");
            return true;
        }
    }


    @Transactional
    public boolean adminExistCheck(String admin_id, String admin_pw){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin admin =adminRepository.findById(admin_id);
        if(admin==null){
            return false;
        }
        else{
            return encoder.matches(admin_pw,admin.getAdmin_pw());
        }
    }

    @Transactional
    public Admin getAdminInfo(String admin_id){

        Admin resAdmin = adminRepository.findById(admin_id);
        return resAdmin;
    }
}
