import java.time.LocalTime;
import java.util.Objects;

public class Vehicle {
    private String licencePlate;

    private LocalTime entryTime;

    private LocalTime exitTime;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public int getAvgSpeed(int sectionLength) {

        double timeDifferenceInHours = (this.exitTime.toSecondOfDay() - this.entryTime.toSecondOfDay()) / 3600.0;

        return (int) (sectionLength / timeDifferenceInHours);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(getLicencePlate(), vehicle.getLicencePlate()) && Objects.equals(getEntryTime(), vehicle.getEntryTime()) && Objects.equals(getExitTime(), vehicle.getExitTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicencePlate(), getEntryTime(), getExitTime());
    }
}
