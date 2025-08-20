package org.aynodkar.taskflow.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Document("journals")
@Data
public class JournalEntry {

    private String journalId;
    private String journalName;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
}
