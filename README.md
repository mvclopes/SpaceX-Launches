
# SpaceX Launches

O aplicativo `SpaceX Launches` foi desenvolvido utilizando o padrão de arquitetura de apresentação [MVVM](https://developer.android.com/jetpack/guide?hl=pt-br) e os dados que são consumidos pela aplicação são lançamentos realizados pela empresa [SpaceX](https://docs.spacexdata.com/).

### Tecnologias utilizadas
Foram utilizadas as seguintes tecnologias:
* Injeção de dependência com [Koin](https://insert-koin.io/)
* Persistência de dados com [Room](https://developer.android.com/training/data-storage/room)
* Requisições a API com [Retrofit](https://square.github.io/retrofit/)
* Navegação entre telas com [Jetpack Navigation](https://developer.android.com/guide/navigation)
* Testes unitários com [Mockk](https://mockk.io/)
* Programação assíncrona com [Flow](https://developer.android.com/kotlin/flow?hl=pt-br) e [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
* Componentes personalizados como [Lottie Animations](https://lottiefiles.com/) e [Circle Image View](https://github.com/hdodenhof/CircleImageView)
* Carregamento de imagens com [Glide](https://bumptech.github.io/glide/)

### Demonstração
<p>
  <img width="300"src="https://user-images.githubusercontent.com/67007295/181764095-b975de8f-0476-44a8-9903-5757a84c8f70.gif">
</p>

### Próximas etapas
* Modularizar as funcionalidades (Ex.: banco de dados, serviço de API, componentes personalizados, etc).
* Criação de testes instrumentados aplicando [Robot Pattern](https://academy.realm.io/posts/kau-jake-wharton-testing-robots/).
* Implementação de novas funcionalidades (Ex.: criação de tela de login, custom views).
