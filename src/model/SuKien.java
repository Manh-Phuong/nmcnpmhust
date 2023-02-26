/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class SuKien {

    private String name;
    private String place;
    private String time;
    private String content;
    private int id;
    private String start;
    private String end;

    

    public SuKien(int id, String name, String place, String time, String content, String start, String end) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.time = time;
        this.content = content;
        this.start = start;
        this.end = end;
    }

    public SuKien(String name, String place, String time) {
        this.name = name;
        this.place = place;
        this.time = time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    
    
    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }
}
