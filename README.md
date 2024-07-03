## TDD Exo 2 (Bowling Frame)

Le but de l'exercice est de poursuivre l’apprentissage du mocking et du TDD, via la réalisation d’une application de bowling virtuel jouant sur l’aléatoire.

On souhaite développer une classe Frame, qui représente une frame dans le jeu du bowling.

1. Créer la classe Frame.
2. Ecrire les tests unitaires pour les méthodes de la classe Frame.
3. Implémenter les méthodes de la classe Frame.

Les tests à écrire pour doivent couvrir les scénarios suivants :
- Chaque lancer augmente le score de la série du nombre de quilles tombées.
- S’il s’agit d’une série standard (round 1 par exemple) :
    - En cas de strike, il ne doit pas être possible de lancer de nouveau pour de la série.
    - En cas de lancers standards, il ne doit pas être possible de lancer plus de 2 fois pour la série.
- S’il s’agit d’une série finale (dernier round) :
    - En cas de strike, il doit être possible de lancer de nouveau pour la série.
    - En cas de strike puis d’un lancer, il doit être possible de lancer de nouveau pour la série.
    - En cas de spare, il doit être possible de lancer de nouveau pour la série.
    - En cas de lancers standards, il ne doit pas être possible de lancer plus de 3 fois pour la série.



## HELP

### Structure des classes
1. La classe ***Pin***
```java
public class Pin {
  private int quantityFalledPin;
}
```
2. La classe ***Frame***
```java
public class Frame {
  private int score;
  private boolean lastFrame;
  private PinGenerator pinGenerator;
  private List<Pin> pins;
  
  public Frame(PinGenerator pinGenerator, boolean lastFrame) {
    this.lastFrame = lastFrame;
    this.pinGenerator = pinGenerator;
  }
  
  public boolean makeRoll(){
    throw new NotImplementedException();
  }
}
```
3. L'interface ***PinGenerator***
```java
public interface PinGenerator {
  int randomFalledPin(int max);
}
```

### Méthodes de tests unitaires
```java
Roll_SimpleFrame_FirstRoll_CheckScore
Roll_SimpleFrame_SecondRoll_CheckScore
Roll_SimpleFrame_FirstRollStrike_ReturnFalse
Roll_SimpleFrame_SecondRoll_ReturnFalse
Roll_LastFrame_FirstRollStrike_ReturnTrue
Roll_LastFrame_SecondRoll_FirstRollStrick_CheckScore
Roll_LastFrame_SecondRoll_FirstRollStrike_ReturnTrue
Roll_LastFrame_ThirdRoll_FirstRollStrick_CheckScore
Roll_LastFrame_SecondRoll_SecondRollSpare_ReturnTrue
Roll_LastFrame_ThirdRoll_SecondRollSpare_CheckScore
Roll_LastFrame_ThirdRoll_ReturnFalse
```