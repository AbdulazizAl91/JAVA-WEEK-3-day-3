package com.example.tasktracker.TaskTrackerController;

import com.example.tasktracker.ApiResponse.ApiResponse;
import com.example.tasktracker.Modle.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task-tracker")
public class TaskTrackerController {

    ArrayList<TaskTracker>taskTrackers=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<TaskTracker> getTaskTrackers(){
        return taskTrackers;
    }
    @PostMapping("/add")
    public ApiResponse addTaskTracker(@RequestBody TaskTracker taskTracker){
        taskTrackers.add(taskTracker);
        return new ApiResponse("task add",200);
    }
    @PutMapping("/update/{index}")
    public ApiResponse updateTaskTracker(@PathVariable int index,@RequestBody TaskTracker taskTracker){
        taskTrackers.set(index,taskTracker);
        return new ApiResponse("task updated",200) ;

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTaskTracker(@PathVariable int index){
        taskTrackers.remove(index);
        return new ApiResponse("task deleted",200);
    }
    @PutMapping("/change/{id}")
    public ApiResponse changeTaskStatus(@PathVariable String id ){

        for (TaskTracker item:taskTrackers){
            if(item.getId().equals(id)){
                if(item.getStatus().equalsIgnoreCase("done")){
                    item.setStatus("not done");
                }
                else {
                    item.setStatus("done");
                }
            }

        }
        return new ApiResponse("task changed",200);
    }
    @GetMapping ("/search/{title}")
    public TaskTracker Search(@PathVariable String title){
        for (TaskTracker item:taskTrackers){
            if(item.getTitle().equalsIgnoreCase(title)){
                return item;
            }

        }
        return new TaskTracker("",title+" not found","","");
    }



}
