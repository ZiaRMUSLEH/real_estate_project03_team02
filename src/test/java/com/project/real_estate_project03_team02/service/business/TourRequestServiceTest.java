package com.project.real_estate_project03_team02.service.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.project.real_estate_project03_team02.entity.concretes.business.*;
import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.entity.enums.TourRequestStatus;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.TourRequestMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.TourRequestRequest;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.TourRequestRepository;
import com.project.real_estate_project03_team02.security.service.UserDetailsServiceImpl;
import com.project.real_estate_project03_team02.service.helper.AdvertServiceHelper;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

class TourRequestServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PageableHelper pageableHelper;

    @Mock
    private TourRequestRepository tourRequestRepository;

    @Mock
    private TourRequestMapper tourRequestMapper;

    @Mock
    private HttpServletRequest httpServletRequest;


    @InjectMocks
    private TourRequestService tourRequestService;
    private TourRequestRequest tourRequestRequest;
    private TourRequestResponse tourRequestResponse;
    private TourRequest tourRequest;
    private User authenticatedUser;
    private Pageable pageable;
    private Page<TourRequest> tourRequestPage;
    private Advert advert;
    private AdvertServiceHelper advertServiceHelper;
    private User ownerUser;







     @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
        // Create sample authenticated user
        authenticatedUser = new User();
        // Create sample pageable
        pageable = Pageable.ofSize(10).withPage(0);
        // Create a sample empty page
        tourRequestPage = new PageImpl<>(Collections.emptyList());

        tourRequest = new TourRequest();
        List<TourRequest> tourRequestList = Collections.singletonList(tourRequest);
        tourRequestPage = new PageImpl<>(tourRequestList, pageable, tourRequestList.size());

        tourRequestRequest = new TourRequestRequest();

         // Set other properties as needed
         tourRequest.setId(1L);

         tourRequestResponse = new TourRequestResponse();
         // Set properties of the response as needed


         tourRequestRequest.setAdvertId(1L);

         advert = new Advert();
         advert.setUserId(new User());
         ownerUser = new User();
        // advertServiceHelper = new AdvertServiceHelper();

    }

     int page = 0;
     int size = 10;
     String sort = "id";
     String type = "asc";

     @Test
     void testGetAllTourRequestOfAuthenticatedUser() {
         // Prepare sample data
         TourRequest tourRequest = createSampleTourRequest();
         Page<TourRequest> tourRequestPageWithData = new PageImpl<>(Collections.singletonList(tourRequest));
         TourRequestResponse tourRequestResponse = createSampleTourRequestResponse();

         // Stub method calls
         when(userService.getAuthenticatedUser(httpServletRequest)).thenReturn(authenticatedUser);
         when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
         when(tourRequestRepository.findAllByGuestUserId(authenticatedUser, pageable)).thenReturn(tourRequestPageWithData);
         when(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest)).thenReturn(tourRequestResponse);

         // Execute the method to test
         Page<TourRequestResponse> result = tourRequestService.getAllTourRequestOfAuthenticatedUser(
                 httpServletRequest, page, size, sort, type);

         // Assertions
         assertNotNull(result, "Result should not be null");
         assertEquals(1, result.getTotalElements(), "Total elements should be 1");
         assertEquals(tourRequestResponse, result.getContent().get(0), "TourRequestResponse should match the expected response");

         // Verify interactions
         verify(userService, times(1)).getAuthenticatedUser(httpServletRequest);
         verify(pageableHelper, times(1)).getPageableWithProperties(page, size, sort, type);
         verify(tourRequestRepository, times(1)).findAllByGuestUserId(authenticatedUser, pageable);
         verify(tourRequestMapper, times(1)).mapTourRequestToTourRequestResponse(tourRequest);
     }


     @Test
      void testGetAllTourRequests_ReturnsPageOfTourRequestResponses() {
         when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
         when(tourRequestRepository.findAll(pageable)).thenReturn(tourRequestPage);
         when(tourRequestMapper.mapTourRequestToTourRequestResponse(any(TourRequest.class))).thenReturn(new TourRequestResponse());

         Page<TourRequestResponse> response = tourRequestService.getAllTourRequests(page, size, sort, type);

         assertNotNull(response);
         assertEquals(1, response.getTotalElements());
         assertEquals(1, response.getContent().size());
         verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
         verify(tourRequestRepository).findAll(pageable);
         verify(tourRequestMapper).mapTourRequestToTourRequestResponse(any(TourRequest.class));
     }

     @Test
      void testGetAllTourRequests_ThrowsResourceNotFoundException_WhenNoTourRequestsFound() {
         when(pageableHelper.getPageableWithProperties(page, size, sort, type)).thenReturn(pageable);
         when(tourRequestRepository.findAll(pageable)).thenReturn(Page.empty());

         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
             tourRequestService.getAllTourRequests(page, size, sort, type);
         });

         assertEquals(ErrorMessages.NO_TOUR_REQUEST_SAVED, exception.getMessage());
         verify(pageableHelper).getPageableWithProperties(page, size, sort, type);
         verify(tourRequestRepository).findAll(pageable);
         verifyNoMoreInteractions(tourRequestMapper);
     }


     @Test
      void testGetTourRequestDetail_UserExistsAndTourRequestFound() {
         // Arrange
         String email = "ritzyhomes.office@gmail.com";
         TourRequest tourRequest = new TourRequest();
         TourRequestResponse tourRequestResponse = new TourRequestResponse();

         when(httpServletRequest.getAttribute("username")).thenReturn(email);
         when(userService.existByEmail(email)).thenReturn(true);
         when(tourRequestRepository.findById(anyLong())).thenReturn(Optional.of(tourRequest));
         when(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest)).thenReturn(tourRequestResponse);

         // Act
         ResponseEntity<TourRequestResponse> responseEntity = tourRequestService.getTourRequestDetail(httpServletRequest, 1L);

         // Assert
         assertNotNull(responseEntity);
         assertEquals(ResponseEntity.ok(tourRequestResponse), responseEntity);
         verify(httpServletRequest).getAttribute("username");
         verify(userService).existByEmail(email);
         verify(tourRequestRepository).findById(1L);
         verify(tourRequestMapper).mapTourRequestToTourRequestResponse(tourRequest);
     }

     @Test
      void testGetTourRequestDetail_UserDoesNotExist() {
         // Arrange
         String email = "ritzyhomes.office@gmail.com";

         when(httpServletRequest.getAttribute("username")).thenReturn(email);
         when(userService.existByEmail(email)).thenReturn(false);

         // Act & Assert
         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
             tourRequestService.getTourRequestDetail(httpServletRequest, 1L);
         });

         assertEquals(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE_BY_EMAIL, email), exception.getMessage());
         verify(httpServletRequest).getAttribute("username");
         verify(userService).existByEmail(email);
         verifyNoInteractions(tourRequestRepository, tourRequestMapper);
     }

     @Test
      void testGetTourRequestDetail_TourRequestNotFound() {
         // Arrange
         String email = "ritzyhomes.office@gmail.com";

         when(httpServletRequest.getAttribute("username")).thenReturn(email);
         when(userService.existByEmail(email)).thenReturn(true);
         when(tourRequestRepository.findById(anyLong())).thenReturn(Optional.empty());

         // Act & Assert
         ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
             tourRequestService.getTourRequestDetail(httpServletRequest, 1L);
         });

         assertEquals(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, 1L), exception.getMessage());
         verify(httpServletRequest).getAttribute("username");
         verify(userService).existByEmail(email);
         verify(tourRequestRepository).findById(1L);
         verifyNoInteractions(tourRequestMapper);
     }


    @Test
     void testGetTourRequestDetailById_Found() {
        // Arrange
        when(tourRequestRepository.findById(1L)).thenReturn(Optional.of(tourRequest));
        when(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest)).thenReturn(tourRequestResponse);

        // Act
        ResponseEntity<TourRequestResponse> response = tourRequestService.getTourRequestDetailById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tourRequestResponse, response.getBody());

        verify(tourRequestRepository, times(1)).findById(1L);
        verify(tourRequestMapper, times(1)).mapTourRequestToTourRequestResponse(tourRequest);
    }

    @Test
     void testGetTourRequestDetailById_NotFound() {
        // Arrange
        when(tourRequestRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            tourRequestService.getTourRequestDetailById(1L);
        });

        assertEquals(String.format(ErrorMessages.NOT_FOUND_TOUR_REQUEST, 1L), exception.getMessage());

        verify(tourRequestRepository, times(1)).findById(1L);
        verify(tourRequestMapper, times(0)).mapTourRequestToTourRequestResponse(any());
    }


    @Test
    void testCheckTourRequestRequestDate_validDate() {
        tourRequestRequest.setTourDate(LocalDate.now().plusDays(1));  // a date in the future

        Assertions.assertDoesNotThrow(() -> tourRequestService.checkTourRequestRequestDate(tourRequestRequest));
    }

    @Test
    void testCheckTourRequestRequestDate_invalidDate() {

        tourRequestRequest.setTourDate(LocalDate.now().minusDays(1));  // a date in the past

        BadRequestException thrown = Assertions.assertThrows(
                BadRequestException.class,
                () -> tourRequestService.checkTourRequestRequestDate(tourRequestRequest)
        );

        Assertions.assertEquals(ErrorMessages.INVALID_TOUR_REQUEST_DATE, thrown.getMessage());
    }

    @Test
    void testCheckTourRequestRequestDate_todayDate() {

        tourRequestRequest.setTourDate(LocalDate.now());  // today's date

        Assertions.assertDoesNotThrow(() -> tourRequestService.checkTourRequestRequestDate(tourRequestRequest));
    }


//    @Test
//    void testSaveTourRequest() {
//        when(tourRequestMapper.mapTourRequestRequestToTourRequest(tourRequestRequest)).thenReturn(tourRequest);
//        when(advertServiceHelper.findById(tourRequestRequest.getAdvertId())).thenReturn(advert);
//        when(advert.getUserId()).thenReturn(ownerUser);
//        when(userService.getAuthenticatedUser(httpServletRequest)).thenReturn(authenticatedUser);
//        when(tourRequestRepository.save(tourRequest)).thenReturn(tourRequest);
//        when(tourRequestMapper.mapTourRequestToTourRequestResponse(tourRequest)).thenReturn(tourRequestResponse);
//
//        ResponseMessage<TourRequestResponse> response = tourRequestService.saveTourRequest(httpServletRequest, tourRequestRequest);
//
//        assertEquals(HttpStatus.CREATED, response.getHttpStatus());
//        assertEquals(SuccessMessages.TOUR_REQUEST_CREATED, response.getMessage());
//        assertEquals(tourRequestResponse, response.getObject());
//
//        verify(tourRequestMapper, times(1)).mapTourRequestRequestToTourRequest(tourRequestRequest);
//        verify(advertServiceHelper, times(1)).findById(tourRequestRequest.getAdvertId());
//        verify(userService, times(1)).getAuthenticatedUser(httpServletRequest);
//        verify(tourRequestRepository, times(1)).save(tourRequest);
//        verify(tourRequestMapper, times(1)).mapTourRequestToTourRequestResponse(tourRequest);
//    }


     private User userCreator (){
         Role role1 = Role.builder()
                .roleName(RoleType.ADMIN)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(role1);


        return User.builder()
                .id(1L)
                .firstName("Admin")
                .lastName("Admin")
                .email("ritzyhomes.office@gmail.com")
                .phone("0000000000")
                .passwordHash("Admin!@#123")
                .resetPasswordCode("resetCode")
                .builtIn(true)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .userRoles(roles)
                .build();

    }

    private TourRequest createSampleTourRequest() {
        Long id = 1L;

        AdvertType advertType = AdvertType.builder()
                .title(AdvertTypes.RENT)
                .builtIn(false)
                .build();


        Country country = new Country(1L,"United States");
        City city = new City(1L,"New York",country);
        District district = new District(1L,"Example District",city);

        Role role1 = Role.builder()
                .roleName(RoleType.ADMIN)
                .build();
        Role role2 = Role.builder()
                .roleName(RoleType.CUSTOMER)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User user = User.builder()
                .firstName("John2")
                .lastName("Doe2")
                .email("john2.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .userRoles(roles)
                .build();

        Category category = Category.builder()
                .title("Your Title")
                .icon("Your Icon")
                .builtIn(false)
                .seq(0)
                .slug("Your Slug")
                .isActive(true)
                .build();

        Advert advert = Advert.builder()
                .id(123456L)
                .title("Sample Title")
                .description("Sample Description")
                .slug("sample-slug")
                .price(100000.00)
                .status(AdvertStatus.ACTIVATED)
                .builtIn(false)
                .isActive(true)
                .viewCount(0)
                .location("Sample Location")
                .advertTypeId(advertType)
                .countryId(country)
                .cityId(city)
                .districtId(district)
                .userId(user)
                .categoryId(category)
                .createdAt(LocalDateTime.now())
                .build();

        User ownerUser = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();

        User guestUser = User.builder()
                .firstName("John1")
                .lastName("Doe1")
                .email("john1.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();


        LocalDate tourDate = LocalDate.of(2024, 5, 12);


        return TourRequest.builder()
                .id(id)
                .tourDate(tourDate)
                .tourTime(LocalTime.now())
                .status(TourRequestStatus.PENDING)
                .advertId(advert)
                .ownerUserId(ownerUser)
                .guestUserId(guestUser)
                .createdAt(LocalDateTime.now())
                .build();
    }
    private TourRequestResponse createSampleTourRequestResponse() {
        Long id = 1L;

        AdvertType advertType = AdvertType.builder()
                .title(AdvertTypes.RENT)
                .builtIn(false)
                .build();


        Country country = new Country(1L,"United States");
        City city = new City(1L,"New York",country);
        District district = new District(1L,"Example District",city);

        Role role1 = Role.builder()
                .roleName(RoleType.ADMIN)
                .build();
        Role role2 = Role.builder()
                .roleName(RoleType.CUSTOMER)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        roles.add(role2);

        User user = User.builder()
                .firstName("John2")
                .lastName("Doe2")
                .email("john2.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(null)
                .userRoles(roles)
                .build();

        Category category = Category.builder()
                .title("Your Title")
                .icon("Your Icon")
                .builtIn(false)
                .seq(0)
                .slug("Your Slug")
                .isActive(true)
                .build();

        Advert advert = Advert.builder()
                .id(123456L)
                .title("Sample Title")
                .description("Sample Description")
                .slug("sample-slug")
                .price(100000.00)
                .status(AdvertStatus.ACTIVATED)
                .builtIn(false)
                .isActive(true)
                .viewCount(0)
                .location("Sample Location")
                .advertTypeId(advertType)
                .countryId(country)
                .cityId(city)
                .districtId(district)
                .userId(user)
                .categoryId(category)
                .createdAt(LocalDateTime.now())
                .build();

        User ownerUser = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();

        User guestUser = User.builder()
                .firstName("John1")
                .lastName("Doe1")
                .email("john1.doe@example.com")
                .phone("124567890")
                .passwordHash("hashedPassword")
                .resetPasswordCode("resetCode")
                .builtIn(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .userRoles(roles)
                .build();


        LocalDate tourDate = LocalDate.of(2024, 5, 12);


        return TourRequestResponse.builder()
                .id(id)
                .tourDate(tourDate)
                .advertId(advert)
                .ownerUserId(ownerUser)
                .guestUserId(guestUser)
                .build();
    }

    private TourRequestRequest createSampleTourRequestRequest() {
        return TourRequestRequest.builder()
                .tourDate(LocalDate.of(2024, 5, 12))
                .tourTime(LocalTime.of(10, 30))
                .advertId(123456L)
                .build();
    }

}
