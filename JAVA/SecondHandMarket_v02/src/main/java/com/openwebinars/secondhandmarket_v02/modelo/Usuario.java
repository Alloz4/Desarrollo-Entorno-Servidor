package com.openwebinars.secondhandmarket_v02.modelo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    private String nombre;
    private String apellidos;
    private String avatar;

    @CreatedDate
    private Date fechaAlta;

    private String email;
    private String password;


    public Usuario() { }


    public Usuario(String nombre, String apellidos, String avatar, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellidos() {
        return apellidos;
    }


    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getAvatar() {
        return avatar;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Date getFechaAlta() {
        return fechaAlta;
    }


    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
        result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fechaAlta == null) ? 0 : fechaAlta.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (apellidos == null) {
            if (other.apellidos != null)
                return false;
        } else if (!apellidos.equals(other.apellidos))
            return false;
        if (avatar == null) {
            if (other.avatar != null)
                return false;
        } else if (!avatar.equals(other.avatar))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fechaAlta == null) {
            if (other.fechaAlta != null)
                return false;
        } else if (!fechaAlta.equals(other.fechaAlta))
            return false;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", avatar=" + avatar
                + ", fechaAlta=" + fechaAlta + ", email=" + email + ", password=" + password + "]";
    }

}
