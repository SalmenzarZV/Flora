# Flora, Una Aplicacion para guardar tus plantas.

El resumen básico de funcionamiento de esta aplicación es simple, te permite ver las plantas que guardes con una gran variedad de información acerca de la misma. 
los campos que puedes guardar sobre la flora son: 

- Nombre (obligatorio para poder guardar).
- Familia.
- Identificación.
- Altitud.
- Habitat.
- Fitosociología.
- Biotipo.
- Biología Reproductiva.
- Floración.
- Fructificación.
- Expresión sexual.
- Polinización .
- Dispersión.
- Número Cromosomatico.
- Reproducción Asexual (Si o no).
- Distribución.
- Biología.
- Demografía.
- Amenazas.
- Medidas Propuestas.

Para almacenar las imágenes disponemos de un servidor en laravel, además de una base de datos en PHPmyadmin.

La aplicación consta de 4 Pantallas ordenadas de la siguinte manera:

![grafico](https://user-images.githubusercontent.com/80886031/156904066-255e57cc-9504-45b1-89ff-0d42f70c15c3.JPG)

# PANTALLA PRINCIPAL (MainActivity): 

Esta Es la pantalla principal de la aplicación, desde la que accedemos a todas las otras pantallas de la aplkicación. En ella se muestran las floras que tenemos agregadas en la base de datos. Cada flora consta de una imagen (En caso de no haber agregado una imagen sale una por defecto), y un texto con el nombre y la familia de la flora. Este elemento es pulsable para redirigir la aplicación a la pantalla de edición de la flora seleccionada (EditFloraActivity)

En las esquinas inferiores encontamos dos botones flotantes para navegar entre las pantallas para añadir una flora (AddFloraActivity) y para añadir una imagen a una flora (AddImageActivity)

- Boton derecho (+): AddFloraActivity
- Boton izquierdo (Camara): AddImageActivity

![Captura1](https://user-images.githubusercontent.com/80886031/156903787-cb4cfaa6-46fd-4e38-93ce-d48565f18d01.JPG)

# PANTALLA PARA AÑADIR FLORAS (AddFloraActivity):

Esta pantalla cuenta con un campo de texto por cada campo que hemos mencionado para añadirle todos los detalles que queramos a la flora (El único  campo obligatorio es el nombre). 

Nuevamente encontraremos dos botones flotantes en las esquinas inferiores de la pantalla:

- Boton derecho (+): Añadir la Flora 
- Boton izquierdo (X): Cancelar

Al confirmar la acción de ambos botones volveremos a MainActivity

![Captura2](https://user-images.githubusercontent.com/80886031/156904079-429b8bb6-a58b-4dec-96ea-dd63a14e0617.JPG)


# PANTALLA PARA AÑADIR IMÁGENES A UNA FLORA (AddImagenActivity):

En esta Pantalla Tendremos un menú desplegable con los nombres de las floras que tenemos añadidas para poder añadirle una foto (es obligatorio seleccionar una flora), y un campo de texto para añadirle una descripción. En caso de que una flora ya tenga una imagen asociada y le añadamos otra, la anterior se sobreescribirá. Si pulsamos en la imagen de la pantalla seremos redirigidos al explorador de imagenes de nuestro dispositivo para seleccionar la imagen que deseemos asociar a una flora. Al seleccionarla la imagen por defecto seria sustituida por la imagen seleccionada.

Esta vez nos encontramos dos botones convencionales en la parte inferior de la foto:

- Boton derecho (Add This Image): Añadir la imagen a la flora 
- Boton izquierdo (Cancel): Cancelar

Al confirmar la acción de ambos botones volveremos a MainActivity

![Captura3](https://user-images.githubusercontent.com/80886031/156904083-233e8ac3-8032-4e24-a069-4234655685b7.JPG)

# PANTALLA PARA EDITAR UNA FLORA EXISTENTE (EditFloraActivity):

Esta pantalla es prácticamente idéntica a AddFloraActivity, con la diferencia que nos aparece la imagen de nuestra flora (o la imagen por defecto) en la parte superior y encontramos un botón más en la parte inferior derecha, justo encima del botón que teniamos anteriormente: 

- Boton derecho Abajo(Lápiz): Editar la flora seleccionada
- Boton derecho Arriba (Basura): Eliminar la flora seleccionada
- Boton izquierdo (X): Cancelar

![Captura4](https://user-images.githubusercontent.com/80886031/156904089-f3248092-388c-4060-9252-ba2872bfcaf3.JPG)

