package onlinerTests;

import io.qameta.allure.Description;
import onliner.pages.CatalogPage;
import onliner.utils.WebDriverRunner;
import onlinerTests.provider.CatalogElementProvider;
import onlinerTests.provider.ComputersNetworksProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CatalogPageTest {
    private static final CatalogPage catalogPage = new CatalogPage();

    @BeforeAll
    public static void init() {
        catalogPage.openPage();
    }

    @BeforeEach
    public void setUp() {
        catalogPage.openCatalogPortal();
    }

    @AfterAll
    public static void tearDown() {
        WebDriverRunner.close();
    }

    @Description("Catalog elements on page")
    @ParameterizedTest
    @ArgumentsSource(CatalogElementProvider.class)
    @DisplayName("Catalog elements on page")
    public void testCatalogElementDisplayedOnPage(List<String> expectedElement) {
        assertTrue(catalogPage.isCatalogElementOnPage(expectedElement));
    }

    @Description("test")
    @ParameterizedTest
    @ArgumentsSource(ComputersNetworksProvider.class)
    @DisplayName("Computers_And_Networks elements on page")
    public void testComputersAndNetworksElementDisplayedOnPage(List<String> expectedElements) {
        catalogPage.selectCategoryComputersAndNetworks();
        assertTrue(catalogPage.isComputersAndNetworksElementsOnPage(expectedElements));
    }

    @Description(" computer has price, name, quantity")
    @Test
    @DisplayName("Component for computer has price, name, quantity")
    public void testComponentForComputerHasPriceNameQuantity() {
        catalogPage.selectCategoryComputersAndNetworks()
                .selectCategoryComponents();
        assertTrue(catalogPage.isAllElementsContainNameQuantityAndPrice());
    }
}