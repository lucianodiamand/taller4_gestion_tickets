package entity;
import java.sql.Date;

import lombok.*;
import jakarta.persistence.*;

@Data 
@Entity
@Table(name = "tickets")
public class Tickets {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private long id;

private String titulo;

private String descripcion;

private String estado;

private String categoria;

private Date fecha_creacion;

private Date fecha_cierre;

private String creado_por;

private String asignado_a;

private Integer id_usuario;


@ManyToOne
@JoinColumn(name = "id_usuario", nullable = false )

private Usuario usuario;


}         


