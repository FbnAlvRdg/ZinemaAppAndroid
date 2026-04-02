package com.example.proyecto_gestion_peliculas.data

import com.example.proyecto_gestion_peliculas.R
import com.example.proyecto_gestion_peliculas.models.Pelicula


fun listarPeliculasPantalla(): List<Pelicula> {
    val pelicula1 = Pelicula(
        "Jackie Brown",
        "Crimen",
        "Quentin Tarantino",
        listOf("Pam Grier", "Samuel L. Jackson", "Robert Forster"),
        1997,
        8.8,
        R.drawable.jackie_brown_poster,
        "Una azafata que trabaja como mensajera de dinero para un traficante de armas es detenida por la policía. Atrapada entre los federales y su peligroso jefe, decide idear un plan para quedarse con el dinero y cambiar su vida. Thriller con diálogos afilados y ritmo pausado."

    )
    val pelicula2 = Pelicula(
        "Her",
        "Drama",
        "Spike Jonze",
        listOf("Joaquin Phoenix", "Scarlett Johansson", "Amy Adams"),
        2013,
        9.2,
        R.drawable.her_poster,
        "Un hombre solitario desarrolla una relación emocional con un sistema operativo dotado de inteligencia artificial. Lo que comienza como una curiosidad tecnológica evoluciona en una historia íntima sobre el amor, la conexión y la soledad en la era digital."
    )
    val pelicula3 = Pelicula(
        "The Big Lebowski",
        "Comedia",
        "Joel & Ethan Coen",
        listOf("Jeff Bridges", "John Goodman", "Julianne Moore"),
        1998,
        8.8,
        R.drawable.big_lebowsky_poster,
        "Un tipo despreocupado conocido como “El Nota” es confundido con un millonario del mismo nombre y se ve envuelto en una absurda trama de secuestros, chantajes y personajes excéntricos. Comedia de culto con humor surrealista y diálogos míticos."
    )
    val pelicula4 = Pelicula(
        "Once Upon a Time in Hollywood",
        "Comedia / Drama",
        "Quentin Tarantino",
        listOf("Leonardo DiCaprio", "Brad Pitt", "Margot Robbie"),
        2019,
        7.6,
        R.drawable.once_upon_hollywood,
        "Un actor de televisión en declive y su doble de acción buscan abrirse camino en Hollywood durante el apogeo de la industria cinematográfica en 1969. Tarantino mezcla ficción histórica, humor y nostalgia cinematográfica."
    )

    val pelicula5 = Pelicula(
        "The Departed",
        "Crimen / Thriller",
        "Martin Scorsese",
        listOf("Leonardo DiCaprio", "Matt Damon", "Jack Nicholson"),
        2006,
        8.5,
        R.drawable.the_departed,
        "En Boston, un policía infiltrado en la mafia y un criminal infiltrado en la policía intentan descubrir la identidad del otro antes de ser descubiertos. Thriller intenso de engaños, traiciones y violencia."
    )

    val pelicula6 = Pelicula(
        "Parásitos",
        "Thriller / Drama",
        "Bong Joon-ho",
        listOf("Kang-ho Song", "Sun-kyun Lee", "Yeo-jeong Jo"),
        2019,
        8.6,
        R.drawable.parasitos_poster,
        "Una familia humilde se infiltra poco a poco en la vida de una familia rica, pero las tensiones sociales y los secretos ocultos desencadenan un conflicto inesperado. Crítica social con humor negro y giros sorprendentes."
    )

    val pelicula7 = Pelicula(
        "A Esmorga",
        "Drama",
        "Ignacio Vilar",
        listOf("Xulio Abonjo", "Luis Tosar", "Marta Lado"),
        2014,
        7.0,
        R.drawable.a_esmorga_poster,
        "Tres amigos se embarcan en una noche de excesos por la ciudad de Ourense, en Galicia, que se convierte en un descenso al caos, la violencia y la decadencia humana. Adaptación de la novela gallega de Eduardo Blanco Amor."
    )

    val pelicula8 = Pelicula(
        "El Resplandor",
        "Terror / Suspense",
        "Stanley Kubrick",
        listOf("Jack Nicholson", "Shelley Duvall", "Danny Lloyd"),
        1980,
        8.4,
        R.drawable.the_shining_poster,
        "Un hombre acepta ser el cuidador de invierno de un hotel aislado junto a su familia, pero fuerzas sobrenaturales y su propia locura lo llevan a un descenso aterrador hacia la violencia. Clásico del terror psicológico con atmósfera inquietante."
    )

    return listOf<Pelicula>(pelicula1, pelicula2, pelicula3, pelicula4, pelicula5, pelicula6, pelicula7, pelicula8)
}