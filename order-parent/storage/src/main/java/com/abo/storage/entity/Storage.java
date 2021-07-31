package com.abo.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Abo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage implements Serializable{
    private static final long serialVersionUID = 5154665188128451884L;
    private Long id;
    private Long productId;
    private Integer total;
    private Integer used;
    private Integer residue;
    private Integer frozen;
}
