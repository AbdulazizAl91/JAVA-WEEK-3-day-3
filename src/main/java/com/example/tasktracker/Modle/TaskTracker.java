package com.example.tasktracker.Modle;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskTracker {
   private String id;
   private String title;
   private String description;
   private String status;


}