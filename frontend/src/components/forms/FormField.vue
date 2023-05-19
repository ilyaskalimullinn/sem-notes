<template>
  <div class="form-field">
    <label :for="id">{{label}}</label>
    <input @input="this.$emit('update:modelValue', $event.target.value)"
           :name="name"
           :type="type"
           :id="id"
           :value="modelValue"
           :placeholder="placeholder"
           :required="required"
    />
  </div>
</template>

<script>
export default {
  name: "FormField",
  emits: ['update:modelValue'],
  props: {
    name: {
      type: String,
      required: true
    },
    required: {
      type: Boolean,
      default: false
    },
    type: {
      type: String,
      default: "text"
    },
    id: {
      type: String,
      default: (props) => props.name
    },
    label: {
      type: String,
      default: (props) => props.name
    },
    placeholder: {
      type: String,
      default: (props) => props.label
    },
    modelValue: {
      type: String,
      default: "modelValue"
    }
  }
}
</script>

<style scoped>

</style>
