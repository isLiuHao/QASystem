package com.lh.entity.medical;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * 重构：疾病的实体  用来保存爬取的数据 子表保存分词
 */
@Data
@Entity
@Table(name = "medical")
public class Medical {
    @Id
    @GeneratedValue
    private Long id;//Id
    @Column(name = "name",unique = true)
    private String name;//病名
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> part;//部位
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> family;//科室

    @Column(columnDefinition="TEXT")
    private String intro;//简介
    @Column(columnDefinition="TEXT")
    private String cause;// 病因
    @Column(columnDefinition="TEXT")
    private String diagnose;//诊断
    @Column(columnDefinition="TEXT")
    private String cure;//治疗
    @Column(columnDefinition="TEXT")
    private String prevent;//预防
    @Column(columnDefinition="TEXT")
    private String complication;//并发症
    @Column(columnDefinition="TEXT")
    private String symptom;//症状

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
    private List<String> cure_list;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> prevent_list;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> complication_list;//并发症词集合
    /**
     * 症状词集合
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name="position")
    private List<String> symptom_list;

    public Medical() {
    }
}
