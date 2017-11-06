package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Created by Dawid on 26.10.2017.
 */
@Embeddable
public class Address {

    @NotNull
    @Column(nullable = false)
    protected String street;
}
