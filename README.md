<h2>exam-trans-sec70</h2>
<h3>En el presente repositorio se puede encontrar la actividad entregable para el exámen transversal</h3>
<h4>Los ítems desarrollados fueron</h4>
<ol>
  <li>Una conexión a la base de datos MySQL correctamente desarrollada</li>
  <li>Una API consumible por sus respectivos métodos HTTP</li>
  <li>Métodos CRUD que permitan extraer, subir, actualizar y eliminar valores</li>
  <li>Una solución a las funciones especiales solicitadas</li>
</ol> 

<h3>Orden de endpoints</h3>
<p>Para probar correctamente la API, es necesario crear en primera instancia un usario a través de la ruta de admin, luego crear un álbum, y las láminas necesarias.</p>
<p>Posteriormente, ya podremos agregar registros a usuario-album y usuario-lamina</p>
<h4>Listado de endpoints</h4>
<ol>
  <li>AdminUsuarioAñadir. http://localhost:8080/api/admin/crear-usuario</li>
  <li>AdminAlbumAñadir. http://localhost:8080/api/admin/album/crear</li>
  <li>AdminLaminaAñadir. http://localhost:8080/api/admin/lamina/crear</li>
  <li>UsuarioAlbumAñadir. http://localhost:8080/api/usuario/album/add</li>
  <li>UsuarioLaminaAñadir. http://localhost:8080/api/usuario/lamina/add</li>
</ol>

<p>Con estos endpoints poblaremos la base de datos y podremos utilizar los endpoints con método GET para obtener la información. Sugerimos utilizar el documento de Postman Collection para probar la API.</p>

<p>La lógica de la base de datos es la siguiente:</p>
<ul>
  <li>El administrador (ruta /admin) es quien puede realizar operaciones CRUD sobre la tabla Álbum y la tabla Lámina. Estas tablas sirven de base principal para almacenar todos los álbumes y láminas que puedan poseer los usuarios</li>
  <li>El usuario solo tiene poder para leer información de la tabla Álbum y la tabla Láminas. Para gestionar su colección propia, podrá modificar la tabla usuario-album y usuario-lamina</li>
  <li>Usuario-album contiene todos aquellos álbumes que tienen los usuarios</li>
  <li>Usuario-lamina contiene las láminas que tienen los usuarios, dando la posibilidad de que un usuario posea láminas de un álbum que NO posee.</li>
</ul>

<p>Para listar láminas repetidas y faltantes relacionamos las tablas de usuario-lámina con lámina, lámina con álbum, y con esto obtenemos la información respectiva de qué láminas le faltan a un usuario para completar sus álbumes. (Este método podrá ser ampliado en el futuro para solo traer láminas faltantes de un álbum en específico)</p>
<p>Respecto a las láminas duplicadas, establecimos una columna de cantidad para las láminas para registrar las copias que se tienen. De igual forma, retorna todas las láminas duplicadas de un usuario.</p>
