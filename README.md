# CodificatorFX3000Lite

Una aplicación de escritorio usando las librerías de JavaFx que permite codificar y decodificar mensajes usando el sistema criptográfico [RSA](https://es.wikipedia.org/wiki/RSA). 

<img src="src/main/resources/ceu/dam/edusoft/img/c3kDay.png" alt="codificator fx 3000 Logo" width="200">
<img src="src/main/resources/ceu/dam/edusoft/img/c3kNight.png" alt="codificator fx 3000 Logo" width="200">


<details>
<summary>Requerimientos</summary>

- repo seguridad Abel-> https://gitlab.com/abel.ceu/psp-dam.git    
- repo javafx Abel-> https://gitlab.com/abel.ceu/interfaces-dam-jfx.git


- Tendremos una pantalla de inicio con un logo de bienvenida.
- Tendremos una barra de menú superior con estas opciones:

    Codificador:
        Cifrar
        Descifrar
    Configuración
        Cargar clave pública
        Cargar clave privada

- Las pantallas de cifrar y descifrar serán dos textAreas con un botón. Un textArea de entrada y otro de salida (este de sólo lectura)
- Las pantallas de cargar clave tendrán la opción de subir un fichero y cargarlo en memoria como certificado público o privado.
</details>

## Demo v1.0

![Demo](https://media.giphy.com/media/fr4tqR3PNJadOEoi6w/giphy.gif)

## Feedback

- Lo de generar las claves al inicio no es necesario. La aplicación es para cifrar y descifrar. Por tanto, cuando yo quiera cifrar, será para enviar un mensaje a alguien cifrado con su clave pública (me la tiene que dar). Y cuando vaya a descifrar algo que me envíen, usaré mi clave privada  (previamente generada porque alguien ha usado la clave pública). En resumen, generar las claves nuevas al inicio no tiene sentido. Sí lo tendría una opción adicional para generar claves, por si el usuario no las tiene de antes.
- No informas al usuario de cómo ha ido la carga de claves. Ni si ya tengo alguna clave cargada.
- Me deja cifrar y descifrar sin cargar previamente certificados. Entiendo que usas el que generas por defecto. Como he comentado antes, no es correcto.
- No controlas el error de descifrado. Por ejemplo, cuando uso una clave privada incorrecta... Boom!! y el usuario no se entera de nada.
- Muy bien el diseño.