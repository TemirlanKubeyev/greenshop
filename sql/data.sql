insert into category (name)
values ('House Plants'),
('Potter Plants'),
('Seeds'),
('Small Plants'),
('Big Plants'),
('Succulents'),
('Trerrariums'),
('Gardening'),
('Accessories');

insert into product (category_id, name, price, size, photo, short_desc, description)
values (1, 'Barberton Daisy', 119, 0, 'files/barberton_daisy.png',
        'The ceramic cylinder planters come with a wooden stand ' ||
        'to help elevate your plants off the ground. The ceramic ' ||
        'cylinder planters come with a wooden stand to help elevate ' ||
        'your plants off the ground.',
        'The ceramic cylinder planters come with a wooden stand to help elevate your plants off the ground. The ceramic cylinder planters come with a wooden stand to help elevate your plants off the ground. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla augue nec est tristique auctor. Donec non est at libero vulputate rutrum. Morbi ornare lectus quis justo gravida semper. Nulla tellus mi, vulputate adipiscing cursus eu, suscipit id nulla.
Pellentesque aliquet, sem eget laoreet ultrices, ipsum metus feugiat sem, quis fermentum turpis eros eget velit. Donec ac tempus ante. Fusce ultricies massa massa. Fusce aliquam, purus eget sagittis vulputate, sapien libero hendrerit est, sed commodo augue nisi non neque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempor, lorem et placerat vestibulum, metus nisi posuere nisl, in accumsan elit odio quis mi. Cras neque metus, consequat et blandit et, luctus a nunc. Etiam gravida vehicula tellus, in imperdiet ligula euismod eget. The ceramic cylinder planters come with a wooden stand to help elevate your plants off the ground. '),
(2, 'Angel Wing Begonia', 169, 1, 'files/Angel_Wing_Begonia.png', null, null),
(3, 'African Violet', 199, 2, 'files/1727807386266Beach_Spider_Lily.png', null, null);

insert into review (score, review_text, product_id)
values (5, 'nice product', 1),
       (4, 'nice product', 1);