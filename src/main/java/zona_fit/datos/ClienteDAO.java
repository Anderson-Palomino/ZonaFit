package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        //Nos permite preparar la sentencia SQL que se va a ejecutar hacia la base de datos
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            //Preparamos la sentencia sql
            ps = con.prepareStatement(sql);
            //Ejecutamos la sentencia
            rs = ps.executeQuery();
            while (rs.next()) {//Llamamos al metodo .next() para preguntar si hay registros a iterar
                //Creas un objeto de tipo cliente para establecer el valor que se va a recuperar
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }

        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            //Como se ve en el sql se esta solicitando un parametro "?", al continuacion
            //Establecemos 1 parametro con el metodo de getId
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        //Los signos de ? se llaman parametros posicionales
        String sql = "INSERT INTO cliente (nombre,apellido,membresia) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET nombre=?,apellido=?,membresia=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM cliente WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        //Listar clientes
////        System.out.println("*** Lista de clientes ***");
//        IClienteDAO clienteDao = new ClienteDAO();


        //buscar por Id
//        var cliente1 = new Cliente(2);
//        System.out.println("Cliente antes de la busqueda: " + cliente1);
//        var encontrado = clienteDao.buscarClientePorId(cliente1);
//        if(encontrado){
//            System.out.println("Cliente encontrado: " + cliente1);
//        }else {
//            System.out.println("Cliente no encontrado: " + cliente1);

        //Agregar cliente
//        var nuevoCliente = new Cliente("Daniel", "Ortiz", 300);
//        var agregado = clienteDao.agregarCliente(nuevoCliente);
//        if (agregado) {
//            System.out.println("Cliente agregado correctamente: " + nuevoCliente);
//        } else {
//            System.out.println("No se ha agregado correctamente: " + nuevoCliente);
//        }

        //Modificar cliente
//        var modificarCliente = new Cliente(7, "Carlos Daniel", "Ortiz", 300);
//        var modificado = clienteDao.modificarCliente(modificarCliente);
//        if (modificado) {
//            System.out.println("Cliente modificado: " + modificarCliente);
//        } else {
//            System.out.println("No se modifico cliente: " + modificarCliente);
//        }

        //Eliminar Clientre
//        var clienteEliminar = new Cliente(7);
//        var eliminado = clienteDao.eliminarCliente(clienteEliminar);
//        if (eliminado) {
//            System.out.println("Cliente eliminado: "+clienteEliminar);
//        }else {
//            System.out.println("Cliente no eliminado: "+clienteEliminar);
//        }
//
//        //Lista clientes
//        var clientes = clienteDao.listarClientes();
//        clientes.forEach(System.out::println);
//    }
}

