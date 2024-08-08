package com.rifqio.springsecurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "notices")
@Getter
@Setter
public class Notices {
    @Id
    @Column(name = "id_notice")
    private long id;

    @Column(name = "notice_summary")
    private String summary;

    @Column(name = "notice_details")
    private String details;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
