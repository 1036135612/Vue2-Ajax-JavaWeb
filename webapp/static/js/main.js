/*----------------------
    Template: Sa- Minimalist Ecommerce html template
    Description: This is html5 template
    Author: Devitems
    Version: 1.0
---------------------------*/
(function ($) {
    "use strict";



    /*-----
          jQuery MeanMenu
     -------------------*/
    jQuery('nav#dropdown').meanmenu();

    /*---
         wow js active
    ------------- */
    new WOW().init();


    /*------
       stickey menu
    ----------------*/
    $(window).on('scroll', function () {
        var scroll = $(window).scrollTop();
        if (scroll < 265) {
            $(".sticky-header").removeClass("sticky");
        } else {
            $(".sticky-header").addClass("sticky");
        }
    });

    /*--
    Nivo Slider
    -----------------------------------*/
    $('#home-slider').nivoSlider({
        directionNav: true,
        animSpeed: 1000,
        effect: 'random',
        slices: 18,
        pauseTime: 88885000,
        pauseOnHover: false,
        controlNav: false,
        prevText: '<i class="fa fa-long-arrow-left"></i>',
        nextText: '<i class="fa fa-long-arrow-right"></i>'
    });

    /*-----
        05. Isotope activation
    ------------------*/
    $(window).on('load', function () {

        // Activate isotope in container
        $(".product-filter").isotope({
            itemSelector: '.filter-item'
        });

        //add isotope click function
        $('.product-filter-menu li').on('click', function () {
            $(".product-filter-menu li").removeClass("active");
            $(this).addClass("active");

            var selector = $(this).attr("data-filter");
            $(".product-filter").isotope({
                filter: selector,
                animationOptions: {
                    duration: 750,
                    easing: 'linear',
                    queue: false
                }
            });
            return false;
        });

    });


    /*-----
        portfolio item 2 activation
    ---------------------*/

    $('.grid').imagesLoaded(function () {

        // filter items on button click
        $('.portfolio-menu').on('click', 'button', function () {
            var filterValue = $(this).attr('data-filter');
            $grid.isotope({ filter: filterValue });
        });

        // init Isotope
        var $grid = $('.grid').isotope({
            itemSelector: '.grid-item',
            percentPosition: true,
            masonry: {
                // use outer width of grid-sizer for columnWidth
                columnWidth: 1
            }
        });

    });

    /*------
        portfolio item 3 activation
    ------------------*/

    $('.grid-2').imagesLoaded(function () {

        // filter items on button click
        $('.portfolio-menu').on('click', 'button', function () {
            var filterValue = $(this).attr('data-filter');
            $grid.isotope({ filter: filterValue });
        });

        // init Isotope
        var $grid = $('.grid-2').isotope({
            itemSelector: '.grid-item',
            percentPosition: true,
            masonry: {
                // use outer width of grid-sizer for columnWidth
                columnWidth: '.grid-item'
            }
        });

    });
    $('.portfolio-menu button').on('click', function (event) {
        $(this).siblings('.active').removeClass('active');
        $(this).addClass('active');
        event.preventDefault();
    });

    /*------
      Counter Up
    -------------- */
    $('.counter').counterUp({
        delay: 70,
        time: 5000
    });
    /* magnificPopup video popup */
    $('.video-play a').magnificPopup({
        type: 'iframe'
    });

    /* magnificPopup image popup */
    /*$('.single-portfolio a').magnificPopup({
        type: 'image',
        gallery:{
            enabled:true
        }
    });*/
    /*------
      slider active
    ---------------- */
    $(".our-brand-list").owlCarousel({
        autoPlay: false,
        slideSpeed: 2000,
        pagination: false,
        navigation: false,
        items: 4,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        itemsDesktop: [1199, 4],
        itemsDesktopSmall: [991, 3],
        itemsTablet: [767, 2],
        itemsMobile: [479, 1],
    });

    $(".portfolio-slide").owlCarousel({
        autoPlay: false,
        slideSpeed: 2000,
        pagination: false,
        navigation: true,
        items: 1,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        itemsDesktop: [1199, 1],
        itemsDesktopSmall: [991, 1],
        itemsTablet: [767, 1],
        itemsMobile: [479, 1],
    });
    $(".related-product-list").owlCarousel({
        autoPlay: false,
        slideSpeed: 2000,
        pagination: false,
        navigation: false,
        items: 3,
        navigationText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
        itemsDesktop: [1199, 3],
        itemsDesktopSmall: [991, 2],
        itemsTablet: [767, 1],
        itemsMobile: [479, 1],
    });

    /*--
    Product Details Thumbnail Slider
    -----------------*/
    $('.pro-thumb-img-slider').slick({
        speed: 700,
        slidesToShow: 4,
        slidesToScroll: 1,
        prevArrow: '<button type="button" class="arrow-prev"><i class="fa fa-angle-left"></i></button>',
        nextArrow: '<button type="button" class="arrow-next"><i class="fa fa-angle-right"></i></button>',
        responsive: [
            {
                breakpoint: 991,
                settings: {
                    slidesToShow: 3,
                }
            },
            {
                breakpoint: 767,
                settings: {
                    slidesToShow: 3,
                }
            }
        ]
    });
    /*--
        Testimonial Slider
    -----------------*/
    $('.testimonial-list').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        dots: false,
        autoplay: true,
        arrows: false,
        centerMode: false,
        responsive: [

            {
                breakpoint: 767,
                settings: {
                    autoplay: true,
                    dots: false,
                    slidesToShow: 1,
                    centerMode: false,
                }
            },
            {
                breakpoint: 480,
                settings: {
                    autoplay: true,
                    dots: false,
                    slidesToShow: 1,
                    centerMode: false,
                }
            }
        ]
    })


    /*------------------
         5. Price Slider
     --------------------------*/
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 2500,
        values: [0, 2000],
        slide: function (event, ui) {
            $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
        }
    });
    $("#amount").val("$" + $("#slider-range").slider("values", 0) +
        " - $" + $("#slider-range").slider("values", 1));


    /*--
    Product Quantity
    -----------------------------------*/
    $('.product-quantity1').append('<span class="dec qtybtn"><i class="fa fa-angle-left"></i></span><span class="inc qtybtn"><i class="fa fa-angle-right"></i></span>');
    $('.qtybtn').on('click', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        if ($button.hasClass('inc')) {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find('input').val(newVal);
    });


    /*--
        Checkout Form Collapse on Checkbox
    -----------------------------------*/
    $('.checkout-form input[type="checkbox"]').on('click', function () {
        var $collapse = $(this).data('target');
        if ($(this).is(':checked')) {
            $('.collapse[data-collapse="' + $collapse + '"]').slideDown();
        } else {
            $('.collapse[data-collapse="' + $collapse + '"]').slideUp();
        }
    })

    /*--
    Product Filter Toggle
    -----------------------------------*/
    $('.product-filter-toggle').on('click', function () {
        $('.product-filter-wrapper').slideToggle();
    })



    /*-------------------------------------------
       scrollUp jquery active
   --------------------------------------------- */
    $.scrollUp({
        scrollText: '<i class="zmdi zmdi-long-arrow-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });




})
    (jQuery);




