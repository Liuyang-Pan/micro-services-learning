package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * purpose:
 *
 * @author Pan Liuyang
 * 2022/11/25 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JavaClientEntity implements Serializable {
    private static final long serialVersionUID = -4147470396604021123L;

    private String id;
    private String name;
    private String address;
    private String title;
}
