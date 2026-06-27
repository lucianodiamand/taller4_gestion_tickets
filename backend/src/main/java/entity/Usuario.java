package entity;
import lombok.*;
import jakarta.persistence.*; // importa herramientas para conectar con la BDD

@Data
@Entity //representa tabla en base de datos
@Table(name = "usuarios") // especificamente con la tabla llamada usuarios
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) //la BDD se encarga de autoincrementar
private Long id;

private String username;
private String password;


@OneToMany(mappedBy = "usuario")
private List<Tickets> tickets;

}




 
