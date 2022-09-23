package com.cmcglobal.kafkastreamprocessor.entities;

import lombok.Data;

import java.util.List;

@Data
public class JiraTicketValue {
    private JiraTicketInfo task = new JiraTicketInfo();
    private List<JiraTicketInfo> subTasks; //not required
}
