package com.calzyr.entity.archive;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "archived_records")
public class ArchiveRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "archived_record_id")
    private Integer Id;

    @Column(name = "record_type")
    private String RecordType;

    @Column(name = "original_record_id")
    private Integer OriginalRecordId;

    @Column(name = "moved_at")
    private Timestamp MovedAt;

    @Column(name = "permanent_deleted_at")
    private Timestamp PermanentDeletedAt;

    @Column(name = "additional_information")
    private String AdditionalInformation;

    @Column(name = "modified_user")
    private String ModifiedUser;

    @Column(name = "modified_user_id")
    private Integer ModifiedUserId;

    @Column(name = "created_at")
    private Timestamp CreatedAt;

    @Column(name = "updated_at")
    private Timestamp UpdatedAt;

    @Column(name = "deleted_at")
    private Timestamp DeletedAt;
}
