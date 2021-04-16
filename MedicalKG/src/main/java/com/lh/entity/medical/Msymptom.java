package com.lh.entity.medical;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * 症状的实体
 */
@Data
@Entity
@Table(name = "msymptom")
public class Msymptom {
    @Id
    @GeneratedValue
    private long id;//Id
    @Column(name = "name",unique = true)
    private String name;//症状名
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name="position")
    private List<String> part;//部位
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name="position")
    private List<String> family;//科室

    @Column(columnDefinition="TEXT")
    private String intro;//简介
    @Column(columnDefinition="TEXT")
    private String cause;// 病因
    @Column(columnDefinition="TEXT")
    private String diagnose;//诊断
    @Column(columnDefinition="TEXT")
    private String examine;//检查

    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> intro_list;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> cause_list;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> diagnose_list;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> examine_list;

    public Msymptom() {
    }
}
