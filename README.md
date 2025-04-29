# App de Tarefas com Firebase In-App Messaging

Este é um aplicativo Android desenvolvido em Java que demonstra o uso do Firebase In-App Messaging para engajar o usuário conforme ele conclui tarefas.

## Funcionalidades

- Lista de tarefas
- Marcação de tarefas concluídas
- Mensagens inteligentes com base no progresso:
  - 1 tarefa: mensagem padrão
  - 50% das tarefas: mensagem motivacional
  - 100% das tarefas: mensagem de conclusão total

## Tecnologias

- Android Studio
- Firebase Analytics
- Firebase In-App Messaging

## Telas

![image](https://github.com/user-attachments/assets/18cc0409-9357-428b-ad14-a85842e7623c)

## Como testar

1. Conecte-se ao Firebase
2. Crie os eventos `tarefa_concluida`, `tarefas_metade`, `todas_tarefas_concluidas` no Console do Firebase
3. Configure campanhas de In-App Messaging associadas a cada evento

---

## 📲 Como configurar o Firebase In-App Messaging

### 1. Configurar o Firebase no projeto

- Acesse o [Firebase Console](https://console.firebase.google.com/) e crie um novo projeto ou selecione um existente.
- Adicione seu aplicativo Android ao projeto no Firebase e baixe o arquivo `google-services.json`.
- Coloque o arquivo `google-services.json` na pasta `app/` do seu projeto Android.

### 2. Adicionar as dependências no `build.gradle`

No arquivo `build.gradle` do módulo do seu aplicativo, adicione as seguintes dependências:

```gradle
implementation platform('com.google.firebase:firebase-bom:32.7.0')
implementation 'com.google.firebase:firebase-inappmessaging-display'
```

Depois disso, sincronize o projeto.

### 3. Inicializar o Firebase no aplicativo

Inicialize o FirebaseAnalytics:
```java
private FirebaseAnalytics firebaseAnalytics;
```

No método `onCreate()` da sua classe `Application` ou `MainActivity`, adicione:

```java
firebaseAnalytics = FirebaseAnalytics.getInstance(this);
```

### 4. Criar campanhas no Firebase Console

1. Acesse **In-App Messaging** no [Firebase Console](https://console.firebase.google.com/)
2. Clique em **Nova campanha**
3. Escolha o tipo de mensagem (Modal, Banner, etc.)
4. Configure o conteúdo da mensagem
5. Na etapa de agendamento, defina um **evento personalizado**, como por exemplo: `evento_personalizado`
6. Publique a campanha

### 5. Acionar eventos no aplicativo

Para exibir a campanha ao clicar em um botão:

```java
Button meuBotao = findViewById(R.id.meu_botao);
meuBotao.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle params = new Bundle();
        firebaseAnalytics.logEvent("evento_personalizado", params);
    }
});
```

> Obs: Certifique-se de que o evento usado aqui seja exatamente o mesmo configurado na campanha.

### 6. Testar a campanha com Installation ID

1. Execute o app e abra o Logcat no Android Studio
2. Procure por uma linha contendo:  
   `Installation ID: XXXXXXXXXXXXX`
3. Copie o ID e vá até o Firebase Console
4. Clique na campanha → **Testar no dispositivo**
5. Cole o Installation ID e clique em **Enviar**

A campanha será exibida imediatamente no app ao acionar o evento.

---

## ⚠️ Considerações Importantes

- **Limites de frequência**: por padrão, o Firebase mostra campanhas no máximo 1 vez por dia por dispositivo. Use o modo de teste para bypassar isso.
- **Nomes de eventos**: os nomes devem ser idênticos entre o código e o Console (case-sensitive).
- **Produção vs Teste**: novas campanhas podem levar até 24h para sincronizar em produção.

---
