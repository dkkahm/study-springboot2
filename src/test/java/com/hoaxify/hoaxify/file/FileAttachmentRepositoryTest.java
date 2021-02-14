package com.hoaxify.hoaxify.file;

import com.hoaxify.hoaxify.hoax.Hoax;
import com.hoaxify.hoaxify.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class FileAttachmentRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    FileAttachmentRepository fileAttachmentRepository;

    @Test
    public void findByDateBeforeAndHoaxIsNull_whenAttachmentsDateOlderThanOneHour_returnsAll() {
        testEntityManager.persist(getOneHourOldFileAttachment());
        testEntityManager.persist(getOneHourOldFileAttachment());
        testEntityManager.persist(getOneHourOldFileAttachment());

        Date oneHourAgo = new Date(System.currentTimeMillis() - (60 * 60 * 1000));

        List<FileAttachment> attachments = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(oneHourAgo);
        assertThat(attachments.size()).isEqualTo(3);
    }

    @Test
    public void findByDateBeforeAndHoaxIsNull_whenAttachmentsDateOlderThanOneHourButHaveHoax_returnsNone() {
        Hoax hoax1 = testEntityManager.persist(TestUtil.createValidHoax());
        Hoax hoax2 = testEntityManager.persist(TestUtil.createValidHoax());
        Hoax hoax3 = testEntityManager.persist(TestUtil.createValidHoax());

        testEntityManager.persist(getOneHourOldFileAttachmentWithHoax(hoax1));
        testEntityManager.persist(getOneHourOldFileAttachmentWithHoax(hoax2));
        testEntityManager.persist(getOneHourOldFileAttachmentWithHoax(hoax3));

        Date oneHourAgo = new Date(System.currentTimeMillis() - (60 * 60 * 1000));

        List<FileAttachment> attachments = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(oneHourAgo);
        assertThat(attachments.size()).isEqualTo(0);
    }

    @Test
    public void findByDateBeforeAndHoaxIsNull_whenAttachmentsDateWithinOneHour_returnsNone() {
        testEntityManager.persist(getFileAttachmentWithinOneHour());
        testEntityManager.persist(getFileAttachmentWithinOneHour());
        testEntityManager.persist(getFileAttachmentWithinOneHour());

        Date oneHourAgo = new Date(System.currentTimeMillis() - (60 * 60 * 1000));

        List<FileAttachment> attachments = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(oneHourAgo);
        assertThat(attachments.size()).isEqualTo(0);
    }

    @Test
    public void findByDateBeforeAndHoaxIsNull_whenSomeAttachmentsOldSomeNewAndSomeWithHoax_returnsAttachmentWithOlderAndNoHoax() {
        Hoax hoax1 = testEntityManager.persist(TestUtil.createValidHoax());
        testEntityManager.persist(getOneHourOldFileAttachmentWithHoax(hoax1));
        testEntityManager.persist(getOneHourOldFileAttachment());
        testEntityManager.persist(getFileAttachmentWithinOneHour());

        Date oneHourAgo = new Date(System.currentTimeMillis() - (60 * 60 * 1000));

        List<FileAttachment> attachments = fileAttachmentRepository.findByDateBeforeAndHoaxIsNull(oneHourAgo);
        assertThat(attachments.size()).isEqualTo(1);
    }

    private FileAttachment getOneHourOldFileAttachment() {
        Date date = new Date(System.currentTimeMillis() - (60 * 60 * 1000) - 1);
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDate(date);
        return fileAttachment;
    }

    private FileAttachment getOneHourOldFileAttachmentWithHoax(Hoax hoax) {
        FileAttachment fileAttachment = getOneHourOldFileAttachment();
        fileAttachment.setHoax(hoax);
        return fileAttachment;
    }

    private FileAttachment getFileAttachmentWithinOneHour() {
        Date date = new Date(System.currentTimeMillis() - (60 * 1000));
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setDate(date);
        return fileAttachment;
    }
}
