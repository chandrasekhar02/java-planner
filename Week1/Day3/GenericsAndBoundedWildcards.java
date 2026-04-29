package Week1.Day3;

import java.util.*;

/**
 * Generics and Bounded Wildcards Tutorial
 * 
 * Generics: Allow you to write type-safe code by specifying a type parameter
 * Wildcards: Provide flexibility in accepting different types
 * Bounded Wildcards: Restrict which types can be passed
 */
public class GenericsAndBoundedWildcards {

   
    static class Box<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

   
    static class AnimalShelter {
     
        public static void printAnimals(List<? extends Animal> animals) {
            for (Animal animal : animals) {
                System.out.println(animal.getName());
            }
        }

        public static double getAverageWeight(List<? extends Animal> animals) {
            double total = 0;
            for (Animal animal : animals) {
                total += animal.getWeight();
            }
            return animals.isEmpty() ? 0 : total / animals.size();
        }
    }

   
    static class AnimalComparator {
 
        public static void sortAnimals(List<Animal> animals, Comparator<? super Animal> comparator) {
            animals.sort(comparator);
        }
    }

 
    static class Printer {
        public static void printList(List<?> list) {
            for (Object item : list) {
                System.out.println(item);
            }
        }

        public static int getSize(List<?> list) {
            return list.size();
        }
    }

  
    static class AnimalProcessor<T extends Animal> {
        public void processAnimal(T animal) {
            System.out.println("Processing: " + animal.getName());
            animal.makeSound();
        }

        public T getOlderAnimal(T animal1, T animal2) {
            return animal1.getAge() > animal2.getAge() ? animal1 : animal2;
        }
    }


    interface Drawable {
        void draw();
    }

    interface Colorable {
        void setColor(String color);
    }

    static class Shape implements Drawable, Colorable {
        private String color = "black";

        @Override
        public void draw() {
            System.out.println("Drawing shape with color: " + color);
        }

        @Override
        public void setColor(String color) {
            this.color = color;
        }
    }

   
    static class RenderEngine<T extends Drawable & Colorable> {
        public void render(T object) {
            object.setColor("red");
            object.draw();
        }
    }

   
    static class Animal {
        private String name;
        private double weight;
        private int age;

        public Animal(String name, double weight, int age) {
            this.name = name;
            this.weight = weight;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }

        public int getAge() {
            return age;
        }

        public void makeSound() {
            System.out.println(name + " makes a sound");
        }
    }

    static class Dog extends Animal {
        public Dog(String name, double weight, int age) {
            super(name, weight, age);
        }

        @Override
        public void makeSound() {
            System.out.println(getName() + " barks!");
        }
    }

    static class Cat extends Animal {
        public Cat(String name, double weight, int age) {
            super(name, weight, age);
        }

        @Override
        public void makeSound() {
            System.out.println(getName() + " meows!");
        }
    }

   
    public static void main(String[] args) {
        System.out.println("GENERICS AND BOUNDED WILDCARDS \n");

        // 1. Simple Generics
        System.out.println("1. SIMPLE GENERICS:");
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println("String Box: " + stringBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println("Integer Box: " + intBox.get() + "\n");

     
        System.out.println("2. UPPER BOUNDED WILDCARD");
        List<Dog> dogs = Arrays.asList(
            new Dog("Rex", 25.0, 5),
            new Dog("Buddy", 30.0, 3)
        );

        List<Cat> cats = Arrays.asList(
            new Cat("Whiskers", 5.0, 2),
            new Cat("Shadow", 4.5, 4)
        );

        System.out.println("Dogs:");
        AnimalShelter.printAnimals(dogs);
        System.out.println("Average dog weight: " + AnimalShelter.getAverageWeight(dogs));

        System.out.println("\nCats:");
        AnimalShelter.printAnimals(cats);
        System.out.println("Average cat weight: " + AnimalShelter.getAverageWeight(cats) + "\n");

       
        System.out.println("3. UNBOUNDED WILDCARD (?):");
        List<String> strings = Arrays.asList("A", "B", "C");
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        System.out.println("String list size: " + Printer.getSize(strings));
        System.out.println("Integer list size: " + Printer.getSize(numbers) + "\n");

      
        System.out.println("4. LOWER BOUNDED WILDCARD ");
        List<Animal> animals = new ArrayList<>(dogs);
        AnimalComparator.sortAnimals(animals, Comparator.comparing(Animal::getWeight));
        System.out.println("Sorted by weight:");
        animals.forEach(a -> System.out.println(a.getName() + ": " + a.getWeight() + "kg"));
        System.out.println();

       
        System.out.println("5. BOUNDED TYPE PARAMETER:");
        AnimalProcessor<Dog> processor = new AnimalProcessor<>();
        Dog dog1 = new Dog("Max", 28.0, 7);
        Dog dog2 = new Dog("Charlie", 25.0, 4);
        processor.processAnimal(dog1);
        System.out.println("Older dog: " + processor.getOlderAnimal(dog1, dog2).getName() + "\n");

  
        System.out.println("6. MULTIPLE BOUNDS ");
        RenderEngine<Shape> engine = new RenderEngine<>();
        engine.render(new Shape());
    }
}
