package com.echanneling.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter

@Entity
@Table(name= "doc_center")
public class DoctorMappingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doc_map_id")
    public int 	docMapId;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private ChannelCenter center;

    @Column(name="start_time")
    public String startTime;

    @Column(name="end_time")
    public String endTime;

    @Column(name="channel_day")
    public String chanelDay;
}
