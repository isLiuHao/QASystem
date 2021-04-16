package com.lh.entity.test;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
//保存网址
@Data
@Entity
@Table(name = "name_href")
public class NameHref {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    private String href;
    @ElementCollection(fetch = FetchType.LAZY)
    @OrderColumn(name="position")
    private List<String> part;

    public NameHref() {
    }

    public NameHref(String name, String href) {
        this.name = name;
        this.href = href;
    }
}
