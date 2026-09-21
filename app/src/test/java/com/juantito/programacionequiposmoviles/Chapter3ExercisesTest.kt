package com.juantito.programacionequiposmoviles

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Chapter3ExercisesTest {
    @Test
    fun topLevelAndExtensionFunctionsReturnExpectedValues() {
        assertEquals(45.0, calculateIGV(250.0), 0.001)
        assertEquals("Hello world", " hello world".capitalizar())
        assertTrue("jean@mail.com".isValidEmail())
        assertEquals(120L, 5.factorial())
    }

    @Test
    fun productExtensionsCalculateAvailabilityAndTax() {
        val product = ProductF("Laptop", 3500.0, 5)

        assertTrue(product.isAvailable())
        assertEquals(4130.0, product.priceWithIGV(), 0.001)
        assertFalse(ProductF("Mouse", 80.0, 0).isAvailable())
    }

    @Test
    fun overloadedOperatorsProduceExpectedResults() {
        val vector = Vector(3.0, 4.0)

        assertEquals(Vector(4.0, 6.0), vector + Vector(1.0, 2.0))
        assertEquals(Vector(6.0, 8.0), vector * 2.0)
        assertEquals(5.0, vector.magnitude(), 0.001)
    }

    @Test
    fun matrixIndexOperatorsStoreAndReadValues() {
        val matrix = Matrix(2, 2)
        matrix[1, 0] = 7.5

        assertEquals(7.5, matrix[1, 0], 0.001)
    }

    @Test
    fun calculatedPropertiesAndPolymorphismReturnExpectedAreas() {
        val circle = CircleField(5.0)
        val shapes: List<ShapeP> = listOf(
            RectangleP("red", 5.0, 3.0),
            TriangleP("green", 6.0, 4.0)
        )

        assertEquals(Math.PI * 25, circle.area, 0.001)
        assertEquals(27.0, calculateTotalArea(shapes), 0.001)
    }

    @Test
    fun dataClassSupportsValueEqualityCopyAndDestructuring() {
        val base = ConfigurationData()
        val dark = base.copy(theme = "dark")
        val point1 = PointData(3, 5)
        val point2 = PointData(3, 5)
        val (success, message, data) = searchUserData(1)

        assertEquals(point1, point2)
        assertEquals("light", base.theme)
        assertEquals("dark", dark.theme)
        assertTrue(success)
        assertEquals("User found", message)
        assertEquals("Jean Torres", data)
    }
}
