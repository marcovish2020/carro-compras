package com.marcovish.carrocomprasboot243;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.*;

import org.springframework.web.bind.annotation.*;


/*
import org.springframework.stereotype.Repository;
import org.springframework.data.Repository.Param;
*/

//import javax.persistence.*;


@RestController
public class AppController {
    @Autowired
    UsuarioRepository usuarioRepo;

    /*
    @RequestParam is used to extract query parameters, form parameters, and even files from the request.
    */
    //http://localhost:8080/api/requestParam?id=21
    @GetMapping("/api/requestParam")
    @ResponseBody
    
    public String getId(@RequestParam(name="id", required = false, defaultValue = "-1") String id) {
    //public String getId(@RequestParam(name="id") Optional<String> id){
    //public String getId(@RequestParam(name="id", required = false) String id) {
    //public String getId(@RequestParam(name="id") String id) {
    //public String getId(@RequestParam String id) {
        return "ID: " + id;
    }

    //http://localhost:8080/api/requestMultiParam?id=15&nombre=juan&apellido=Sanchez
    @GetMapping("/api/requestMultiParam")
    @ResponseBody
    public String getMapValues(@RequestParam Map<String,String> allParams) {
        return "Parameters are " + allParams.entrySet();
    }

    @GetMapping("/api/requestMultiParam2")
    @ResponseBody
    public String getMapValues(@RequestParam List<String> id) {
        return "IDs: " + id;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Im basing my code in  the auto binding of return objects to json by Spring
    @PutMapping("/usuario/{id}")
    public Object updateTask(@PathVariable("id") int id, @RequestBody Usuario u ){
        if(u.getEmail() == null || u.getPassword().trim().equals("")){
            return new  ErrorResponse("Task description is required", 400);
        }
        try{
        usuarioRepo.updateUsuario(u.getEmail(), u.getPassword(), u.getId());
        }
        catch(Exception e){
            if(e.getMessage().contains("Empty results (or something like that)")){
                //return new ErrorResponse("Cannot find task with given id", 404);
                return new String("Cannot find task with given id");
            }else{
                return new String("Something went wrong thit this request. Contact technical support. :)");
            }

        }
        return u;

    }

    @PostMapping("/usuario")
    public Object addUsuario(@RequestBody Usuario u){
        System.out.println("usuario recibido: " + u);
        Usuario ubd= u;
        try{
            ubd= usuarioRepo.save(u);
            }
        catch(Exception e){
            return new ErrorResponse("Cannot save the user", 400);
            }
        return ubd;
    }

    @GetMapping("/usuario")
    public Object searchUsuarioByEmailPassword(Usuario u){
        System.out.println("usuario recibido: " + u);
        Usuario ubd= u;
        try{
            ubd= usuarioRepo.selectUsuario(u.getEmail(), u.getPassword());
            }
        catch(Exception e){
            return new ErrorResponse("Cannot find task with given email and password", 400);
            }
        return ubd;
    }

    @GetMapping("/usuario/{id}")
    //@Produces({MediaType.APPLICATION_JSON})
    //@Consumes({MediaType.APPLICATION_JSON})
    public Object searchUsuarioById(@PathVariable("id") Long id){
        System.out.println("usuario id recibido: " + id);
        Usuario ubd= null;
        try{    
            //usuarioRepo.getOne(id); //Genera un error de serializacion
            Optional<Usuario> ou= usuarioRepo.findById(id);
            if(ou.isPresent()){
                ubd = ou.get();
            }
            if(ubd == null){
                return new ErrorResponse("Cannot find task with given id", 400);
                //ubd =  new Usuario();
                //ubd.setId(-1L);
            }
            }
        catch(Exception e){
                    return new ErrorResponse("Cannot find task with given id", 400);
                    //e.printStackTrace();
            }
        return ubd;
    }
}
