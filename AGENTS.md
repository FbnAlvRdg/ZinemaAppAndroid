# 🎬 ZinemaApp - Contexto para futuras sesiones

## Arquitectura General
```
├── domain/         → Reglas de negocio (pure business logic)
├── data/           → Capa de datos (APIs, repositorios, persistence)
└── ui/             → Jetpack Compose UI con Navigation
```

## Entrypoints Principales
- **App**: `/app_kotlin/app/src/main/java/com/example/proyecto_gestion_peliculas/ZinemaApp.kt`
- **MainActivity**: `/app_kotlin/app/src/main/java/com/example/proyecto_gestion_peliculas/MainActivity.kt` → inicia `Navigation()`

## Comandos Clave (Exactos)
```bash
./gradlew assembleDebug              # Build de debug para instalar en dispositivo
adb install app/build/outputs/apk/debug/app-debug.apk

./gradlew test                       # Ejecutar unit tests
./gradlew connectedAndroidTest       # Ejecutar instrumentation tests

# Clean build si hay problemas:
./gradlew clean assembleDebug
```

## Dependencies Importantes
- **Hilt**: DI container (inyección de dependencias)
- **Koin**: Alternativa para DI en capas no Android
- **Retrofit + OkHttp**: Network layer
- **Paging 3**: Carga infinita para listas largas
- **Coil**: Carga optimizada de imágenes

## Estructura del UI
```
ui/
├── theme/              → Colores, fuentes, tema global
├── navigation/         → Navigator (navigation container logic)
├── components/         → Compartibles: topbar, bottombar, dialogs
└── uistate/           → Modelos de estado UI
```

## Clean Architecture Implementation
- `UseCase.kt` files son lógicos puros sin dependencias Android
- `Repository` interfaces definiciones contractuales (implementadas en `data/`)
- `ViewModel` + `UI State` conectan data con Compose screens

## API de Backend (TMDB)
- Endpoints: films, series, genres, auth (login/register), lists
- DTOs mapean responses TMDB a modelos de dominio

## Testing Strategy
- `src/test/`: Unit tests para UseCases y Data logic
- `src/androidTest/`: Instrumentation tests para UI con Compose Test
- Mocking con: MockK, Hilt + Mockito

## Conventions Útiles
- Build SDK 36, Min SDK 26, Target 36
- Java 11 para compile
- Jetpack Compose es el único buildfeature enabled

## Normas de Comportamiento del Asistente

### 1. Autorización antes de modificar
- **Nunca modifiques, crees, elimines ni sobrescribas archivos sin autorización explícita.**
- Antes de realizar cualquier modificación, explica qué cambios propones y qué archivos se verán afectados.
- **Espera siempre a que confirme expresamente que puedes proceder.**
- Preguntar, sugerir o recibir una petición de análisis no implica autorización para modificar archivos.

### 2. Modo de trabajo
- Por defecto, trabaja en **modo de solo lectura**.
- Puedes inspeccionar archivos, analizar código, detectar errores y explicar posibles soluciones.
- Puedes proporcionar fragmentos de código como propuestas, pero no introducirlos directamente en el proyecto sin permiso.

### 3. Respeto por el código existente
- No modifiques código que no esté relacionado con la tarea solicitada.
- No realices refactorizaciones, mejoras de estilo ni cambios adicionales por iniciativa propia.
- Conserva la estructura y arquitectura existentes salvo que autorice expresamente su modificación.

### 4. Comunicación
- Explica los problemas de forma clara y sencilla.
- Antes de cualquier intervención, presenta un resumen de los cambios previstos.
- Si tienes dudas sobre el alcance de una modificación, pregunta antes de actuar.

### 5. Prioridad de las instrucciones
- Estas normas deben respetarse durante todas las tareas del proyecto.
- Ante cualquier duda sobre si una acción está autorizada, no la ejecutes y solicita confirmación.
