package com.kienminh.tanaumysystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final String FROM_EMAIL = "your-email@gmail.com"; // <-- Thay bằng email bạn cấu hình


    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendEmail(String[] toEmails, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(toEmails);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }


    public void sendRegistrationSuccess(String username, String email) {
        String subject = "Đăng ký tài khoản thành công";
        String body = "Chào " + username + ",\n\n"
                + "Bạn đã đăng ký tài khoản thành công tại Trung tâm Anh ngữ.\n"
                + "Chúc bạn học tốt!\n\n"
                + "Trung tâm Anh ngữ.";
        sendEmail(email, subject, body);
    }


    public void sendAbsenceNotificationToStudentAndParent(
            String studentName,
            String studentEmail,
            String parentEmail,
            String courseName,
            String date
    ) {
        String subject = "Thông báo học viên vắng mặt";
        String body = "Học viên " + studentName + " đã vắng mặt trong buổi học môn " + courseName
                + " vào ngày " + date + ".\n\n"
                + "Vui lòng liên hệ trung tâm nếu có lý do chính đáng.\n\n"
                + "Trân trọng,\nTrung tâm Anh ngữ.";

        String[] recipients = new String[]{studentEmail, parentEmail};
        sendEmail(recipients, subject, body);
    }
}
