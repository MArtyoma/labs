import java.util.Random;

public class MainClass {
  public static void main(String[] args) {
    Random random = new Random();

    int bmxCount = random.nextInt(10);

    for (int i = 0; i < bmxCount; i++) {
      new BMX("BMX " + i, random.nextInt(20) + 2000, true, true);
    }

    int mbCount = random.nextInt(10);

    for (int i = 0; i < mbCount; i++) {
      new MountainBike("Mount Bike " + i, random.nextInt(20) + 2000, true,
          true);
    }

    int childrenBikeCount = random.nextInt(10);

    for (int i = 0; i < childrenBikeCount; i++) {
      new ChildrenBike("Children Bike " + i, random.nextInt(20) + 2000, true,
          true);
    }

    // BMX bmx2 = new BMX("BMX 2", 2019, true, false);
    // BMX bmx3 = new BMX("BMX 3", 2016, false, true);
    // MountainBike mb1 = new MountainBike("Mount Bike 1", 2021, true, true);
    // ChildrenBike cb1 = new ChildrenBike("CB 1", 2021, true, true);
    // ChildrenBike cb2 = new ChildrenBike("CB 2", 2024, true, true);

    System.out
        .println(Bicycle.getInstanceCount(MountainBike.class) + " Mount Bike");
    System.out
        .println(Bicycle.getInstanceCount(ChildrenBike.class) + " Child Bike");
    System.out.println(Bicycle.getInstanceCount(BMX.class) + " BMX");
  }
}
